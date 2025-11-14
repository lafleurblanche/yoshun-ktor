package net.konohana.sakuya.yoshun.services.third

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.third.Third01Dto
import net.konohana.sakuya.yoshun.dtos.third.Third01FrontendDto
import net.konohana.sakuya.yoshun.models.routes.ThirdRoutes
import net.konohana.sakuya.yoshun.models.third.Third01
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Third01Service {
    private fun resultRowThird01(row: ResultRow) = Third01Dto(
        id = row[Third01.id],
        routeID = row[Third01.routeID],
        fromStaCode = row[Third01.fromStaCode],
        toStaCode = row[Third01.toStaCode],
        staCode = row[Third01.staCode],
        staName = row[Third01.staName],
    )

    private fun resultRowThird01Frontend(row: ResultRow): Third01FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Third01.staName]
        val (staName1, staName2) = when {
            staName.length >= MIN_LENGTH_FOR_SPLIT -> Pair(
                // 先頭から2文字を取得
                staName.take(SPLIT_LENGTH),
                // 2文字目以降を取得し、そこから2文字を取得 (4文字目まで)
                staName.drop(SPLIT_LENGTH).take(SPLIT_LENGTH)
            )
            else -> Pair(staName, "")
        }

        // 2. viaRouteName 取得とブランク化ロジック
        // LEFT JOINの結果、対応するレコードがない場合はnullになるため、
        // ?: "" (エルビス演算子) でnullの場合にブランクを設定します。
        val viaRouteName = row.getOrNull(ThirdRoutes.viaRouteName) ?: ""

        // 3. Frontend DTOの生成
        return Third01FrontendDto(
            id = row[Third01.id],
            viaRouteName = viaRouteName,
            staCode = row[Third01.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getThird01Frontend(): List<Third01FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Third01
                // LEFT JOIN を使用して ThirdRoutes テーブルと結合
                .leftJoin(
                    ThirdRoutes,
                    { Third01.routeID },
                    { ThirdRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowThird01Frontend)
        }
    }

    suspend fun getThird01(): List<Third01Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Third01.selectAll().map(::resultRowThird01)
        }
    }

    suspend fun getThird01ByStaCode(staCode: String): Third01Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Third01.selectAll()
                .where { Third01.staCode eq staCode }
                .singleOrNull()?.let {
                    Third01Dto(
                        id = it[Third01.id],
                        routeID = it[Third01.routeID],
                        staCode = it[Third01.staCode],
                        fromStaCode = it[Third01.fromStaCode],
                        toStaCode = it[Third01.toStaCode],
                        staName = it[Third01.staName],
                    )
                }
        }
    }
}
