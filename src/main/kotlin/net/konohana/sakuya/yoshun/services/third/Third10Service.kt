package net.konohana.sakuya.yoshun.services.third

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.third.Third10Dto
import net.konohana.sakuya.yoshun.dtos.third.Third10FrontendDto
import net.konohana.sakuya.yoshun.models.routes.ThirdRoutes
import net.konohana.sakuya.yoshun.models.third.Third10
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Third10Service {
    private fun resultRowThird10(row: ResultRow) = Third10Dto(
        id = row[Third10.id],
        routeID = row[Third10.routeID],
        fromStaCode = row[Third10.fromStaCode],
        toStaCode = row[Third10.toStaCode],
        staCode = row[Third10.staCode],
        staName = row[Third10.staName],
    )

    private fun resultRowThird10Frontend(row: ResultRow): Third10FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Third10.staName]
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
        return Third10FrontendDto(
            id = row[Third10.id],
            viaRouteName = viaRouteName,
            staCode = row[Third10.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getThird10Frontend(): List<Third10FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Third10
                // LEFT JOIN を使用して ThirdRoutes テーブルと結合
                .leftJoin(
                    ThirdRoutes,
                    { Third10.routeID },
                    { ThirdRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowThird10Frontend)
        }
    }

    suspend fun getThird10(): List<Third10Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Third10.selectAll().map(::resultRowThird10)
        }
    }

    suspend fun getThird10ByStaCode(staCode: String): Third10Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Third10.selectAll()
                .where { Third10.staCode eq staCode }
                .singleOrNull()?.let {
                    Third10Dto(
                        id = it[Third10.id],
                        routeID = it[Third10.routeID],
                        staCode = it[Third10.staCode],
                        fromStaCode = it[Third10.fromStaCode],
                        toStaCode = it[Third10.toStaCode],
                        staName = it[Third10.staName],
                    )
                }
        }
    }
}
