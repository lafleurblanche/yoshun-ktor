package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra12Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra12FrontendDto
import net.konohana.sakuya.yoshun.models.quadra.Quadra12
import net.konohana.sakuya.yoshun.models.routes.QuadraRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Quadra12Service {
    private fun resultRowQuadra12(row: ResultRow) = Quadra12Dto(
        id = row[Quadra12.id],
        routeID = row[Quadra12.routeID],
        fromStaCode = row[Quadra12.fromStaCode],
        toStaCode = row[Quadra12.toStaCode],
        staCode = row[Quadra12.staCode],
        staName = row[Quadra12.staName],
    )

    private fun resultRowQuadra12Frontend(row: ResultRow): Quadra12FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Quadra12.staName]
        val (staName1, staName2) = when {
            staName.length >= 4 -> Pair(staName.take(2), staName.substring(2, 4))
            else -> Pair(staName, "")
        }

        // 2. viaRouteName 取得とブランク化ロジック
        // LEFT JOINの結果、対応するレコードがない場合はnullになるため、
        // ?: "" (エルビス演算子) でnullの場合にブランクを設定します。
        val viaRouteName = row.getOrNull(QuadraRoutes.viaRouteName) ?: ""

        // 3. Frontend DTOの生成
        return Quadra12FrontendDto(
            id = row[Quadra12.id],
            viaRouteName = viaRouteName,
            staCode = row[Quadra12.staCode],
            staName1 = staName1,
            staName2 = staName2,
            staName = staName,
        )
    }

    suspend fun getQuadra12Frontend(): List<Quadra12FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra12
                // LEFT JOIN を使用して QuadraRoutes テーブルと結合
                .leftJoin(
                    QuadraRoutes,
                    { Quadra12.routeID },
                    { QuadraRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowQuadra12Frontend)
        }
    }

    suspend fun getQuadra12(): List<Quadra12Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra12.selectAll().map(::resultRowQuadra12)
        }
    }

    suspend fun getQuadra12ByStaCode(staCode: String): Quadra12Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra12.selectAll()
                .where { Quadra12.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra12Dto(
                        id = it[Quadra12.id],
                        routeID = it[Quadra12.routeID],
                        staCode = it[Quadra12.staCode],
                        fromStaCode = it[Quadra12.fromStaCode],
                        toStaCode = it[Quadra12.toStaCode],
                        staName = it[Quadra12.staName],
                    )
                }
        }
    }
}
