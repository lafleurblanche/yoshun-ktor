package net.konohana.sakuya.yoshun.services.third

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.third.Third05Dto
import net.konohana.sakuya.yoshun.dtos.third.Third05FrontendDto
import net.konohana.sakuya.yoshun.models.routes.ThirdRoutes
import net.konohana.sakuya.yoshun.models.third.Third05
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Third05Service {
    private fun resultRowThird05(row: ResultRow) = Third05Dto(
        id = row[Third05.id],
        routeID = row[Third05.routeID],
        fromStaCode = row[Third05.fromStaCode],
        toStaCode = row[Third05.toStaCode],
        staCode = row[Third05.staCode],
        staName = row[Third05.staName],
    )

    private fun resultRowThird05Frontend(row: ResultRow): Third05FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Third05.staName]
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
        return Third05FrontendDto(
            id = row[Third05.id],
            viaRouteName = viaRouteName,
            staCode = row[Third05.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getThird05Frontend(): List<Third05FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Third05
                // LEFT JOIN を使用して ThirdRoutes テーブルと結合
                .leftJoin(
                    ThirdRoutes,
                    { Third05.routeID },
                    { ThirdRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowThird05Frontend)
        }
    }

    suspend fun getThird05(): List<Third05Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Third05.selectAll().map(::resultRowThird05)
        }
    }

    suspend fun getThird05ByStaCode(staCode: String): Third05Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Third05.selectAll()
                .where { Third05.staCode eq staCode }
                .singleOrNull()?.let {
                    Third05Dto(
                        id = it[Third05.id],
                        routeID = it[Third05.routeID],
                        staCode = it[Third05.staCode],
                        fromStaCode = it[Third05.fromStaCode],
                        toStaCode = it[Third05.toStaCode],
                        staName = it[Third05.staName],
                    )
                }
        }
    }
}
