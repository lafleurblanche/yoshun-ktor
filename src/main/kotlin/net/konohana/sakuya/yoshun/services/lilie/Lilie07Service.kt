package net.konohana.sakuya.yoshun.services.lilie

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.lilie.Lilie07Dto
import net.konohana.sakuya.yoshun.models.lilie.Lilie07
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Lilie07Service {
    private fun resultRowLilie07(row: ResultRow) = Lilie07Dto(
        id = row[Lilie07.id],
        routeID = row[Lilie07.routeID],
        fromStaCode = row[Lilie07.fromStaCode],
        toStaCode = row[Lilie07.toStaCode],
        staCode = row[Lilie07.staCode],
        staName = row[Lilie07.staName],
    )

    suspend fun getLilie07(): List<Lilie07Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Lilie07.selectAll().map(::resultRowLilie07)
        }
    }

    suspend fun getLilie07ByStaCode(staCode: String): Lilie07Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Lilie07.selectAll()
                .where { Lilie07.staCode eq staCode }
                .singleOrNull()?.let {
                    Lilie07Dto(
                        id = it[Lilie07.id],
                        routeID = it[Lilie07.routeID],
                        staCode = it[Lilie07.staCode],
                        fromStaCode = it[Lilie07.fromStaCode],
                        toStaCode = it[Lilie07.toStaCode],
                        staName = it[Lilie07.staName],
                    )
                }
        }
    }
}
