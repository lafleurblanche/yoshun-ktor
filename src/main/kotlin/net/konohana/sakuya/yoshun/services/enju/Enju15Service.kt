package net.konohana.sakuya.yoshun.services.enju

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.enju.Enju15Dto
import net.konohana.sakuya.yoshun.models.enju.Enju15
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Enju15Service {
    private fun resultRowEnju15(row: ResultRow) = Enju15Dto(
        id = row[Enju15.id],
        routeID = row[Enju15.routeID],
        fromStaCode = row[Enju15.fromStaCode],
        toStaCode = row[Enju15.toStaCode],
        staCode = row[Enju15.staCode],
        staName = row[Enju15.staName],
    )

    suspend fun getEnju15(): List<Enju15Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Enju15.selectAll().map(::resultRowEnju15)
        }
    }

    suspend fun getEnju15ByStaCode(staCode: String): Enju15Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Enju15.selectAll()
                .where { Enju15.staCode eq staCode }
                .singleOrNull()?.let {
                    Enju15Dto(
                        id = it[Enju15.id],
                        routeID = it[Enju15.routeID],
                        staCode = it[Enju15.staCode],
                        fromStaCode = it[Enju15.fromStaCode],
                        toStaCode = it[Enju15.toStaCode],
                        staName = it[Enju15.staName],
                    )
                }
        }
    }
}
