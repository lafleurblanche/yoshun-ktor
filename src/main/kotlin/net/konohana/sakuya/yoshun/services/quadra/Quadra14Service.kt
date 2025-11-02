package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra14Dto
import net.konohana.sakuya.yoshun.models.quadra.Quadra14
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Quadra14Service {
    private fun resultRowQuadra14(row: ResultRow) = Quadra14Dto(
        id = row[Quadra14.id],
        routeID = row[Quadra14.routeID],
        fromStaCode = row[Quadra14.fromStaCode],
        toStaCode = row[Quadra14.toStaCode],
        staCode = row[Quadra14.staCode],
        staName = row[Quadra14.staName],
    )

    suspend fun getQuadra14(): List<Quadra14Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra14.selectAll().map(::resultRowQuadra14)
        }
    }

    suspend fun getQuadra14ByStaCode(staCode: String): Quadra14Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra14.selectAll()
                .where { Quadra14.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra14Dto(
                        id = it[Quadra14.id],
                        routeID = it[Quadra14.routeID],
                        staCode = it[Quadra14.staCode],
                        fromStaCode = it[Quadra14.fromStaCode],
                        toStaCode = it[Quadra14.toStaCode],
                        staName = it[Quadra14.staName],
                    )
                }
        }
    }
}
