package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra08Dto
import net.konohana.sakuya.yoshun.models.quadra.Quadra08
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Quadra08Service {
    private fun resultRowQuadra08(row: ResultRow) = Quadra08Dto(
        id = row[Quadra08.id],
        routeID = row[Quadra08.routeID],
        fromStaCode = row[Quadra08.fromStaCode],
        toStaCode = row[Quadra08.toStaCode],
        staCode = row[Quadra08.staCode],
        staName = row[Quadra08.staName],
    )

    suspend fun getQuadra08(): List<Quadra08Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra08.selectAll().map(::resultRowQuadra08)
        }
    }

    suspend fun getQuadra08ByStaCode(staCode: String): Quadra08Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra08.selectAll()
                .where { Quadra08.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra08Dto(
                        id = it[Quadra08.id],
                        routeID = it[Quadra08.routeID],
                        staCode = it[Quadra08.staCode],
                        fromStaCode = it[Quadra08.fromStaCode],
                        toStaCode = it[Quadra08.toStaCode],
                        staName = it[Quadra08.staName],
                    )
                }
        }
    }
}
