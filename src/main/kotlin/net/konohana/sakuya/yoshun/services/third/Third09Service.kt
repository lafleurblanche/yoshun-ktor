package net.konohana.sakuya.yoshun.services.third

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.third.Third09Dto
import net.konohana.sakuya.yoshun.dtos.third.Third09FrontendDto
import net.konohana.sakuya.yoshun.models.routes.ThirdRoutes
import net.konohana.sakuya.yoshun.models.third.Third09
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Third09Service {
    private fun resultRowThird09(row: ResultRow) = Third09Dto(
        id = row[Third09.id],
        routeID = row[Third09.routeID],
        fromStaCode = row[Third09.fromStaCode],
        toStaCode = row[Third09.toStaCode],
        staCode = row[Third09.staCode],
        staName = row[Third09.staName],
    )

    private fun resultRowThird09Frontend(row: ResultRow): Third09FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Third09.staName]
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
        return Third09FrontendDto(
            id = row[Third09.id],
            viaRouteName = viaRouteName,
            staCode = row[Third09.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getThird09Frontend(): List<Third09FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Third09
                // LEFT JOIN を使用して ThirdRoutes テーブルと結合
                .leftJoin(
                    ThirdRoutes,
                    { Third09.routeID },
                    { ThirdRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowThird09Frontend)
        }
    }

    suspend fun getThird09(): List<Third09Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Third09.selectAll().map(::resultRowThird09)
        }
    }

    suspend fun getThird09ByStaCode(staCode: String): Third09Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Third09.selectAll()
                .where { Third09.staCode eq staCode }
                .singleOrNull()?.let {
                    Third09Dto(
                        id = it[Third09.id],
                        routeID = it[Third09.routeID],
                        staCode = it[Third09.staCode],
                        fromStaCode = it[Third09.fromStaCode],
                        toStaCode = it[Third09.toStaCode],
                        staName = it[Third09.staName],
                    )
                }
        }
    }
}
