package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra04Dto
import net.konohana.sakuya.yoshun.models.quadra.Quadra04
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Quadra04Service {
    private fun resultRowQuadra04(row: ResultRow) = Quadra04Dto(
        id = row[Quadra04.id],
        routeID = row[Quadra04.routeID],
        fromStaCode = row[Quadra04.fromStaCode],
        toStaCode = row[Quadra04.toStaCode],
        staCode = row[Quadra04.staCode],
        staName = row[Quadra04.staName],
    )

    suspend fun getQuadra04(): List<Quadra04Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra04.selectAll().map(::resultRowQuadra04)
        }
    }

    suspend fun getQuadra04ByStaCode(staCode: String): Quadra04Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra04.selectAll()
                .where { Quadra04.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra04Dto(
                        id = it[Quadra04.id],
                        routeID = it[Quadra04.routeID],
                        staCode = it[Quadra04.staCode],
                        fromStaCode = it[Quadra04.fromStaCode],
                        toStaCode = it[Quadra04.toStaCode],
                        staName = it[Quadra04.staName],
                    )
                }
        }
    }
}
