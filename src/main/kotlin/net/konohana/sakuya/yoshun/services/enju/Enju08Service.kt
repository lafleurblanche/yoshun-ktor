package net.konohana.sakuya.yoshun.services.enju

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.enju.Enju08Dto
import net.konohana.sakuya.yoshun.models.enju.Enju08
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Enju08Service {
    private fun resultRowEnju08(row: ResultRow) = Enju08Dto(
        id = row[Enju08.id],
        routeID = row[Enju08.routeID],
        fromStaCode = row[Enju08.fromStaCode],
        toStaCode = row[Enju08.toStaCode],
        staCode = row[Enju08.staCode],
        staName = row[Enju08.staName],
    )

    suspend fun getEnju08(): List<Enju08Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Enju08.selectAll().map(::resultRowEnju08)
        }
    }

    suspend fun getEnju08ByStaCode(staCode: String): Enju08Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Enju08.selectAll()
                .where { Enju08.staCode eq staCode }
                .singleOrNull()?.let {
                    Enju08Dto(
                        id = it[Enju08.id],
                        routeID = it[Enju08.routeID],
                        staCode = it[Enju08.staCode],
                        fromStaCode = it[Enju08.fromStaCode],
                        toStaCode = it[Enju08.toStaCode],
                        staName = it[Enju08.staName],
                    )
                }
        }
    }
}
