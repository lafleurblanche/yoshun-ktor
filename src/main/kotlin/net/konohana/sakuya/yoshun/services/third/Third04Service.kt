package net.konohana.sakuya.yoshun.services.third

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.third.Third04Dto
import net.konohana.sakuya.yoshun.dtos.third.Third04FrontendDto
import net.konohana.sakuya.yoshun.models.routes.ThirdRoutes
import net.konohana.sakuya.yoshun.models.third.Third04
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Third04Service {
    private fun resultRowThird04(row: ResultRow) = Third04Dto(
        id = row[Third04.id],
        routeID = row[Third04.routeID],
        fromStaCode = row[Third04.fromStaCode],
        toStaCode = row[Third04.toStaCode],
        staCode = row[Third04.staCode],
        staName = row[Third04.staName],
    )

    private fun resultRowThird04Frontend(row: ResultRow): Third04FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Third04.staName]
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
        return Third04FrontendDto(
            id = row[Third04.id],
            viaRouteName = viaRouteName,
            staCode = row[Third04.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getThird04Frontend(): List<Third04FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Third04
                // LEFT JOIN を使用して ThirdRoutes テーブルと結合
                .leftJoin(
                    ThirdRoutes,
                    { Third04.routeID },
                    { ThirdRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowThird04Frontend)
        }
    }

    suspend fun getThird04(): List<Third04Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Third04.selectAll().map(::resultRowThird04)
        }
    }

    suspend fun getThird04ByStaCode(staCode: String): Third04Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Third04.selectAll()
                .where { Third04.staCode eq staCode }
                .singleOrNull()?.let {
                    Third04Dto(
                        id = it[Third04.id],
                        routeID = it[Third04.routeID],
                        staCode = it[Third04.staCode],
                        fromStaCode = it[Third04.fromStaCode],
                        toStaCode = it[Third04.toStaCode],
                        staName = it[Third04.staName],
                    )
                }
        }
    }
}
