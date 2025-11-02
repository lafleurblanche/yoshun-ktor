package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra03Dto
import net.konohana.sakuya.yoshun.models.quadra.Quadra03
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Quadra03Service {
    private fun resultRowQuadra03(row: ResultRow) = Quadra03Dto(
        id = row[Quadra03.id],
        routeID = row[Quadra03.routeID],
        fromStaCode = row[Quadra03.fromStaCode],
        toStaCode = row[Quadra03.toStaCode],
        staCode = row[Quadra03.staCode],
        staName = row[Quadra03.staName],
    )

    suspend fun getQuadra03(): List<Quadra03Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra03.selectAll().map(::resultRowQuadra03)
        }
    }

    suspend fun getQuadra03ByStaCode(staCode: String): Quadra03Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra03.selectAll()
                .where { Quadra03.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra03Dto(
                        id = it[Quadra03.id],
                        routeID = it[Quadra03.routeID],
                        staCode = it[Quadra03.staCode],
                        fromStaCode = it[Quadra03.fromStaCode],
                        toStaCode = it[Quadra03.toStaCode],
                        staName = it[Quadra03.staName],
                    )
                }
        }
    }
}
