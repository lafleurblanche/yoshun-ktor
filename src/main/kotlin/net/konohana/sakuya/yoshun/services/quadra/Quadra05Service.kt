package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra05Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra05FrontendDto
import net.konohana.sakuya.yoshun.models.quadra.Quadra05
import net.konohana.sakuya.yoshun.models.routes.QuadraRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Quadra05Service {
    private fun resultRowQuadra05(row: ResultRow) = Quadra05Dto(
        id = row[Quadra05.id],
        routeID = row[Quadra05.routeID],
        fromStaCode = row[Quadra05.fromStaCode],
        toStaCode = row[Quadra05.toStaCode],
        staCode = row[Quadra05.staCode],
        staName = row[Quadra05.staName],
    )

    private fun resultRowQuadra05Frontend(row: ResultRow): Quadra05FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Quadra05.staName]
        val (staName1, staName2) = when {
            staName.length >= 4 -> Pair(staName.take(2), staName.substring(2, 4))
            else -> Pair(staName, "")
        }

        // 2. viaRouteName 取得とブランク化ロジック
        // LEFT JOINの結果、対応するレコードがない場合はnullになるため、
        // ?: "" (エルビス演算子) でnullの場合にブランクを設定します。
        val viaRouteName = row.getOrNull(QuadraRoutes.viaRouteName) ?: ""

        // 3. Frontend DTOの生成
        return Quadra05FrontendDto(
            id = row[Quadra05.id],
            viaRouteName = viaRouteName,
            staCode = row[Quadra05.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getQuadra05Frontend(): List<Quadra05FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra05
                // LEFT JOIN を使用して QuadraRoutes テーブルと結合
                .leftJoin(
                    QuadraRoutes,
                    { Quadra05.routeID },
                    { QuadraRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowQuadra05Frontend)
        }
    }

    suspend fun getQuadra05(): List<Quadra05Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra05.selectAll().map(::resultRowQuadra05)
        }
    }

    suspend fun getQuadra05ByStaCode(staCode: String): Quadra05Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra05.selectAll()
                .where { Quadra05.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra05Dto(
                        id = it[Quadra05.id],
                        routeID = it[Quadra05.routeID],
                        staCode = it[Quadra05.staCode],
                        fromStaCode = it[Quadra05.fromStaCode],
                        toStaCode = it[Quadra05.toStaCode],
                        staName = it[Quadra05.staName],
                    )
                }
        }
    }
}
