package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra13Dto
import net.konohana.sakuya.yoshun.models.quadra.Quadra13
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Quadra13Service {
    private fun resultRowQuadra13(row: ResultRow) = Quadra13Dto(
        id = row[Quadra13.id],
        routeID = row[Quadra13.routeID],
        fromStaCode = row[Quadra13.fromStaCode],
        toStaCode = row[Quadra13.toStaCode],
        staCode = row[Quadra13.staCode],
        staName = row[Quadra13.staName],
    )

    suspend fun getQuadra13(): List<Quadra13Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra13.selectAll().map(::resultRowQuadra13)
        }
    }

    suspend fun getQuadra13ByStaCode(staCode: String): Quadra13Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra13.selectAll()
                .where { Quadra13.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra13Dto(
                        id = it[Quadra13.id],
                        routeID = it[Quadra13.routeID],
                        staCode = it[Quadra13.staCode],
                        fromStaCode = it[Quadra13.fromStaCode],
                        toStaCode = it[Quadra13.toStaCode],
                        staName = it[Quadra13.staName],
                    )
                }
        }
    }
}
