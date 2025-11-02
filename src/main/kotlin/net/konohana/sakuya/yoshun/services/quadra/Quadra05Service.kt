package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra05Dto
import net.konohana.sakuya.yoshun.models.quadra.Quadra05
import org.jetbrains.exposed.sql.ResultRow
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
