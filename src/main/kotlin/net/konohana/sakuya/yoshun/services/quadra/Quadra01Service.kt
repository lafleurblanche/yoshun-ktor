package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra01Dto
import net.konohana.sakuya.yoshun.models.quadra.Quadra01
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Quadra01Service {
    private fun resultRowQuadra01(row: ResultRow) = Quadra01Dto(
        id = row[Quadra01.id],
        routeID = row[Quadra01.routeID],
        fromStaCode = row[Quadra01.fromStaCode],
        toStaCode = row[Quadra01.toStaCode],
        staCode = row[Quadra01.staCode],
        staName = row[Quadra01.staName],
    )

    suspend fun getQuadra01(): List<Quadra01Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra01.selectAll().map(::resultRowQuadra01)
        }
    }

    suspend fun getQuadra01ByStaCode(staCode: String): Quadra01Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra01.selectAll()
                .where { Quadra01.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra01Dto(
                        id = it[Quadra01.id],
                        routeID = it[Quadra01.routeID],
                        staCode = it[Quadra01.staCode],
                        fromStaCode = it[Quadra01.fromStaCode],
                        toStaCode = it[Quadra01.toStaCode],
                        staName = it[Quadra01.staName],
                    )
                }
        }
    }
}
