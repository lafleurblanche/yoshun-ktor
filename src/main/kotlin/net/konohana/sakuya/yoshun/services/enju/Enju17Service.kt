package net.konohana.sakuya.yoshun.services.enju

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.enju.Enju17Dto
import net.konohana.sakuya.yoshun.models.enju.Enju17
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Enju17Service {
    private fun resultRowEnju17(row: ResultRow) = Enju17Dto(
        id = row[Enju17.id],
        routeID = row[Enju17.routeID],
        fromStaCode = row[Enju17.fromStaCode],
        toStaCode = row[Enju17.toStaCode],
        staCode = row[Enju17.staCode],
        staName = row[Enju17.staName],
    )

    suspend fun getEnju17(): List<Enju17Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Enju17.selectAll().map(::resultRowEnju17)
        }
    }

    suspend fun getEnju17ByStaCode(staCode: String): Enju17Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Enju17.selectAll()
                .where { Enju17.staCode eq staCode }
                .singleOrNull()?.let {
                    Enju17Dto(
                        id = it[Enju17.id],
                        routeID = it[Enju17.routeID],
                        staCode = it[Enju17.staCode],
                        fromStaCode = it[Enju17.fromStaCode],
                        toStaCode = it[Enju17.toStaCode],
                        staName = it[Enju17.staName],
                    )
                }
        }
    }
}
