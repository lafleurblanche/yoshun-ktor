package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra12Dto
import net.konohana.sakuya.yoshun.models.quadra.Quadra12
import org.jetbrains.exposed.sql.ResultRow
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
