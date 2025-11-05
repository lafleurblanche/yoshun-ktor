package net.konohana.sakuya.yoshun.services.enju

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.enju.Enju07Dto
import net.konohana.sakuya.yoshun.models.enju.Enju07
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Enju07Service {
    private fun resultRowEnju07(row: ResultRow) = Enju07Dto(
        id = row[Enju07.id],
        routeID = row[Enju07.routeID],
        fromStaCode = row[Enju07.fromStaCode],
        toStaCode = row[Enju07.toStaCode],
        staCode = row[Enju07.staCode],
        staName = row[Enju07.staName],
    )

    suspend fun getEnju07(): List<Enju07Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Enju07.selectAll().map(::resultRowEnju07)
        }
    }

    suspend fun getEnju07ByStaCode(staCode: String): Enju07Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Enju07.selectAll()
                .where { Enju07.staCode eq staCode }
                .singleOrNull()?.let {
                    Enju07Dto(
                        id = it[Enju07.id],
                        routeID = it[Enju07.routeID],
                        staCode = it[Enju07.staCode],
                        fromStaCode = it[Enju07.fromStaCode],
                        toStaCode = it[Enju07.toStaCode],
                        staName = it[Enju07.staName],
                    )
                }
        }
    }
}
