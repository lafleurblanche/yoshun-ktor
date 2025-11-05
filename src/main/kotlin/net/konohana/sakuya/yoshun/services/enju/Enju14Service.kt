package net.konohana.sakuya.yoshun.services.enju

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.enju.Enju14Dto
import net.konohana.sakuya.yoshun.models.enju.Enju14
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Enju14Service {
    private fun resultRowEnju14(row: ResultRow) = Enju14Dto(
        id = row[Enju14.id],
        routeID = row[Enju14.routeID],
        fromStaCode = row[Enju14.fromStaCode],
        toStaCode = row[Enju14.toStaCode],
        staCode = row[Enju14.staCode],
        staName = row[Enju14.staName],
    )

    suspend fun getEnju14(): List<Enju14Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Enju14.selectAll().map(::resultRowEnju14)
        }
    }

    suspend fun getEnju14ByStaCode(staCode: String): Enju14Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Enju14.selectAll()
                .where { Enju14.staCode eq staCode }
                .singleOrNull()?.let {
                    Enju14Dto(
                        id = it[Enju14.id],
                        routeID = it[Enju14.routeID],
                        staCode = it[Enju14.staCode],
                        fromStaCode = it[Enju14.fromStaCode],
                        toStaCode = it[Enju14.toStaCode],
                        staName = it[Enju14.staName],
                    )
                }
        }
    }
}
