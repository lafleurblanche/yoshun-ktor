package net.konohana.sakuya.yoshun.services.third

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.third.Third02Dto
import net.konohana.sakuya.yoshun.dtos.third.Third02FrontendDto
import net.konohana.sakuya.yoshun.models.routes.ThirdRoutes
import net.konohana.sakuya.yoshun.models.third.Third02
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Third02Service {
    private fun resultRowThird02(row: ResultRow) = Third02Dto(
        id = row[Third02.id],
        routeID = row[Third02.routeID],
        fromStaCode = row[Third02.fromStaCode],
        toStaCode = row[Third02.toStaCode],
        staCode = row[Third02.staCode],
        staName = row[Third02.staName],
    )

    private fun resultRowThird02Frontend(row: ResultRow): Third02FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Third02.staName]
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
        return Third02FrontendDto(
            id = row[Third02.id],
            viaRouteName = viaRouteName,
            staCode = row[Third02.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getThird02Frontend(): List<Third02FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Third02
                // LEFT JOIN を使用して ThirdRoutes テーブルと結合
                .leftJoin(
                    ThirdRoutes,
                    { Third02.routeID },
                    { ThirdRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowThird02Frontend)
        }
    }

    suspend fun getThird02(): List<Third02Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Third02.selectAll().map(::resultRowThird02)
        }
    }

    suspend fun getThird02ByStaCode(staCode: String): Third02Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Third02.selectAll()
                .where { Third02.staCode eq staCode }
                .singleOrNull()?.let {
                    Third02Dto(
                        id = it[Third02.id],
                        routeID = it[Third02.routeID],
                        staCode = it[Third02.staCode],
                        fromStaCode = it[Third02.fromStaCode],
                        toStaCode = it[Third02.toStaCode],
                        staName = it[Third02.staName],
                    )
                }
        }
    }
}
