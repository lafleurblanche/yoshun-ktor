package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra08Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra08FrontendDto
import net.konohana.sakuya.yoshun.models.quadra.Quadra08
import net.konohana.sakuya.yoshun.models.routes.QuadraRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Quadra08Service {
    private fun resultRowQuadra08(row: ResultRow) = Quadra08Dto(
        id = row[Quadra08.id],
        routeID = row[Quadra08.routeID],
        fromStaCode = row[Quadra08.fromStaCode],
        toStaCode = row[Quadra08.toStaCode],
        staCode = row[Quadra08.staCode],
        staName = row[Quadra08.staName],
    )

    private fun resultRowQuadra08Frontend(row: ResultRow): Quadra08FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Quadra08.staName]
        val (staName1, staName2) = when {
            staName.length >= 4 -> Pair(staName.take(2), staName.substring(2, 4))
            else -> Pair(staName, "")
        }

        // 2. viaRouteName 取得とブランク化ロジック
        // LEFT JOINの結果、対応するレコードがない場合はnullになるため、
        // ?: "" (エルビス演算子) でnullの場合にブランクを設定します。
        val viaRouteName = row.getOrNull(QuadraRoutes.viaRouteName) ?: ""

        // 3. Frontend DTOの生成
        return Quadra08FrontendDto(
            id = row[Quadra08.id],
            viaRouteName = viaRouteName,
            staCode = row[Quadra08.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getQuadra08Frontend(): List<Quadra08FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra08
                // LEFT JOIN を使用して QuadraRoutes テーブルと結合
                .leftJoin(
                    QuadraRoutes,
                    { Quadra08.routeID },
                    { QuadraRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowQuadra08Frontend)
        }
    }

    suspend fun getQuadra08(): List<Quadra08Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra08.selectAll().map(::resultRowQuadra08)
        }
    }

    suspend fun getQuadra08ByStaCode(staCode: String): Quadra08Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra08.selectAll()
                .where { Quadra08.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra08Dto(
                        id = it[Quadra08.id],
                        routeID = it[Quadra08.routeID],
                        staCode = it[Quadra08.staCode],
                        fromStaCode = it[Quadra08.fromStaCode],
                        toStaCode = it[Quadra08.toStaCode],
                        staName = it[Quadra08.staName],
                    )
                }
        }
    }
}
