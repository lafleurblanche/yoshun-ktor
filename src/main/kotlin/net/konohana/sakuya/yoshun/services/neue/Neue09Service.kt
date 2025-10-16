package net.konohana.sakuya.yoshun.services.neue

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.neue.Neue09Dto
import net.konohana.sakuya.yoshun.models.neue.Neue09
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Neue09Service {
    private fun resultRowNeue09(row: ResultRow) = Neue09Dto(
        id = row[Neue09.id],
        routeID = row[Neue09.routeID],
        fromStaCode = row[Neue09.fromStaCode],
        toStaCode = row[Neue09.toStaCode],
        staCode = row[Neue09.staCode],
        staName = row[Neue09.staName],
    )

    suspend fun getNeue09(): List<Neue09Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Neue09.selectAll().map(::resultRowNeue09)
        }
    }

    suspend fun getNeue09ByStaCode(staCode: String): Neue09Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Neue09.selectAll()
                .where { Neue09.staCode eq staCode }
                .singleOrNull()?.let {
                    Neue09Dto(
                        id = it[Neue09.id],
                        routeID = it[Neue09.routeID],
                        staCode = it[Neue09.staCode],
                        fromStaCode = it[Neue09.fromStaCode],
                        toStaCode = it[Neue09.toStaCode],
                        staName = it[Neue09.staName],
                    )
                }
        }
    }
}
