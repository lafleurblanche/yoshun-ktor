package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra15Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra15FrontendDto
import net.konohana.sakuya.yoshun.models.quadra.Quadra15
import net.konohana.sakuya.yoshun.models.routes.QuadraRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Quadra15Service {
    private fun resultRowQuadra15(row: ResultRow) = Quadra15Dto(
        id = row[Quadra15.id],
        routeID = row[Quadra15.routeID],
        fromStaCode = row[Quadra15.fromStaCode],
        toStaCode = row[Quadra15.toStaCode],
        staCode = row[Quadra15.staCode],
        staName = row[Quadra15.staName],
    )

    private fun resultRowQuadra15Frontend(row: ResultRow): Quadra15FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Quadra15.staName]
        val (staName1, staName2) = when {
            staName.length >= 4 -> Pair(staName.take(2), staName.substring(2, 4))
            else -> Pair(staName, "")
        }

        // 2. viaRouteName 取得とブランク化ロジック
        // LEFT JOINの結果、対応するレコードがない場合はnullになるため、
        // ?: "" (エルビス演算子) でnullの場合にブランクを設定します。
        val viaRouteName = row.getOrNull(QuadraRoutes.viaRouteName) ?: ""

        // 3. Frontend DTOの生成
        return Quadra15FrontendDto(
            id = row[Quadra15.id],
            viaRouteName = viaRouteName,
            staCode = row[Quadra15.staCode],
            staName1 = staName1,
            staName2 = staName2,
            staName = staName,
        )
    }

    suspend fun getQuadra15Frontend(): List<Quadra15FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra15
                // LEFT JOIN を使用して QuadraRoutes テーブルと結合
                .leftJoin(
                    QuadraRoutes,
                    { Quadra15.routeID },
                    { QuadraRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowQuadra15Frontend)
        }
    }

    suspend fun getQuadra15(): List<Quadra15Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra15.selectAll().map(::resultRowQuadra15)
        }
    }

    suspend fun getQuadra15ByStaCode(staCode: String): Quadra15Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra15.selectAll()
                .where { Quadra15.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra15Dto(
                        id = it[Quadra15.id],
                        routeID = it[Quadra15.routeID],
                        staCode = it[Quadra15.staCode],
                        fromStaCode = it[Quadra15.fromStaCode],
                        toStaCode = it[Quadra15.toStaCode],
                        staName = it[Quadra15.staName],
                    )
                }
        }
    }
}
