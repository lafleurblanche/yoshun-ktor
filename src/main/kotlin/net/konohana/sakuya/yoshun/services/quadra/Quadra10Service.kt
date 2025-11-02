package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra10Dto
import net.konohana.sakuya.yoshun.models.quadra.Quadra10
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Quadra10Service {
    private fun resultRowQuadra10(row: ResultRow) = Quadra10Dto(
        id = row[Quadra10.id],
        routeID = row[Quadra10.routeID],
        fromStaCode = row[Quadra10.fromStaCode],
        toStaCode = row[Quadra10.toStaCode],
        staCode = row[Quadra10.staCode],
        staName = row[Quadra10.staName],
    )

    suspend fun getQuadra10(): List<Quadra10Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra10.selectAll().map(::resultRowQuadra10)
        }
    }

    suspend fun getQuadra10ByStaCode(staCode: String): Quadra10Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra10.selectAll()
                .where { Quadra10.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra10Dto(
                        id = it[Quadra10.id],
                        routeID = it[Quadra10.routeID],
                        staCode = it[Quadra10.staCode],
                        fromStaCode = it[Quadra10.fromStaCode],
                        toStaCode = it[Quadra10.toStaCode],
                        staName = it[Quadra10.staName],
                    )
                }
        }
    }
}
