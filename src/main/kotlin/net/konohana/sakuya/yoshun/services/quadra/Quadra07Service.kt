package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra07Dto
import net.konohana.sakuya.yoshun.models.quadra.Quadra07
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Quadra07Service {
    private fun resultRowQuadra07(row: ResultRow) = Quadra07Dto(
        id = row[Quadra07.id],
        routeID = row[Quadra07.routeID],
        fromStaCode = row[Quadra07.fromStaCode],
        toStaCode = row[Quadra07.toStaCode],
        staCode = row[Quadra07.staCode],
        staName = row[Quadra07.staName],
    )

    suspend fun getQuadra07(): List<Quadra07Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra07.selectAll().map(::resultRowQuadra07)
        }
    }

    suspend fun getQuadra07ByStaCode(staCode: String): Quadra07Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra07.selectAll()
                .where { Quadra07.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra07Dto(
                        id = it[Quadra07.id],
                        routeID = it[Quadra07.routeID],
                        staCode = it[Quadra07.staCode],
                        fromStaCode = it[Quadra07.fromStaCode],
                        toStaCode = it[Quadra07.toStaCode],
                        staName = it[Quadra07.staName],
                    )
                }
        }
    }
}
