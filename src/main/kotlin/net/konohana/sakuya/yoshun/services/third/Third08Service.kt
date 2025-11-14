package net.konohana.sakuya.yoshun.services.third

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.third.Third08Dto
import net.konohana.sakuya.yoshun.dtos.third.Third08FrontendDto
import net.konohana.sakuya.yoshun.models.routes.ThirdRoutes
import net.konohana.sakuya.yoshun.models.third.Third08
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Third08Service {
    private fun resultRowThird08(row: ResultRow) = Third08Dto(
        id = row[Third08.id],
        routeID = row[Third08.routeID],
        fromStaCode = row[Third08.fromStaCode],
        toStaCode = row[Third08.toStaCode],
        staCode = row[Third08.staCode],
        staName = row[Third08.staName],
    )

    private fun resultRowThird08Frontend(row: ResultRow): Third08FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Third08.staName]
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
        return Third08FrontendDto(
            id = row[Third08.id],
            viaRouteName = viaRouteName,
            staCode = row[Third08.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getThird08Frontend(): List<Third08FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Third08
                // LEFT JOIN を使用して ThirdRoutes テーブルと結合
                .leftJoin(
                    ThirdRoutes,
                    { Third08.routeID },
                    { ThirdRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowThird08Frontend)
        }
    }

    suspend fun getThird08(): List<Third08Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Third08.selectAll().map(::resultRowThird08)
        }
    }

    suspend fun getThird08ByStaCode(staCode: String): Third08Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Third08.selectAll()
                .where { Third08.staCode eq staCode }
                .singleOrNull()?.let {
                    Third08Dto(
                        id = it[Third08.id],
                        routeID = it[Third08.routeID],
                        staCode = it[Third08.staCode],
                        fromStaCode = it[Third08.fromStaCode],
                        toStaCode = it[Third08.toStaCode],
                        staName = it[Third08.staName],
                    )
                }
        }
    }
}
