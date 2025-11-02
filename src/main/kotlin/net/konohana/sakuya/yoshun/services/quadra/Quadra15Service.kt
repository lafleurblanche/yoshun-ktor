package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra15Dto
import net.konohana.sakuya.yoshun.models.quadra.Quadra15
import org.jetbrains.exposed.sql.ResultRow
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
