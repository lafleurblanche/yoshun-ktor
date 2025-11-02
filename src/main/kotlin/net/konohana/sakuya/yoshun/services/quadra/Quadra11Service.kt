package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra11Dto
import net.konohana.sakuya.yoshun.models.quadra.Quadra11
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Quadra11Service {
    private fun resultRowQuadra11(row: ResultRow) = Quadra11Dto(
        id = row[Quadra11.id],
        routeID = row[Quadra11.routeID],
        fromStaCode = row[Quadra11.fromStaCode],
        toStaCode = row[Quadra11.toStaCode],
        staCode = row[Quadra11.staCode],
        staName = row[Quadra11.staName],
    )

    suspend fun getQuadra11(): List<Quadra11Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra11.selectAll().map(::resultRowQuadra11)
        }
    }

    suspend fun getQuadra11ByStaCode(staCode: String): Quadra11Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra11.selectAll()
                .where { Quadra11.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra11Dto(
                        id = it[Quadra11.id],
                        routeID = it[Quadra11.routeID],
                        staCode = it[Quadra11.staCode],
                        fromStaCode = it[Quadra11.fromStaCode],
                        toStaCode = it[Quadra11.toStaCode],
                        staName = it[Quadra11.staName],
                    )
                }
        }
    }
}
