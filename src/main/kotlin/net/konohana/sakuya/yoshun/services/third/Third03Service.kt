package net.konohana.sakuya.yoshun.services.third

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.third.Third03Dto
import net.konohana.sakuya.yoshun.dtos.third.Third03FrontendDto
import net.konohana.sakuya.yoshun.models.routes.ThirdRoutes
import net.konohana.sakuya.yoshun.models.third.Third03
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Third03Service {
    private fun resultRowThird03(row: ResultRow) = Third03Dto(
        id = row[Third03.id],
        routeID = row[Third03.routeID],
        fromStaCode = row[Third03.fromStaCode],
        toStaCode = row[Third03.toStaCode],
        staCode = row[Third03.staCode],
        staName = row[Third03.staName],
    )

    private fun resultRowThird03Frontend(row: ResultRow): Third03FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Third03.staName]
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
        return Third03FrontendDto(
            id = row[Third03.id],
            viaRouteName = viaRouteName,
            staCode = row[Third03.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getThird03Frontend(): List<Third03FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Third03
                // LEFT JOIN を使用して ThirdRoutes テーブルと結合
                .leftJoin(
                    ThirdRoutes,
                    { Third03.routeID },
                    { ThirdRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowThird03Frontend)
        }
    }

    suspend fun getThird03(): List<Third03Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Third03.selectAll().map(::resultRowThird03)
        }
    }

    suspend fun getThird03ByStaCode(staCode: String): Third03Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Third03.selectAll()
                .where { Third03.staCode eq staCode }
                .singleOrNull()?.let {
                    Third03Dto(
                        id = it[Third03.id],
                        routeID = it[Third03.routeID],
                        staCode = it[Third03.staCode],
                        fromStaCode = it[Third03.fromStaCode],
                        toStaCode = it[Third03.toStaCode],
                        staName = it[Third03.staName],
                    )
                }
        }
    }
}
