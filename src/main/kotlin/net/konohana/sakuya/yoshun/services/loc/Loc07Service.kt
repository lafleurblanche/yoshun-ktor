package net.konohana.sakuya.yoshun.services.loc

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.loc.Loc07Dto
import net.konohana.sakuya.yoshun.models.loc.Loc07
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Loc07Service {
    private fun resultRowLoc07(row: ResultRow) = Loc07Dto(
        id = row[Loc07.id],
        routeID = row[Loc07.routeID],
        fromStaCode = row[Loc07.fromStaCode],
        toStaCode = row[Loc07.toStaCode],
        staCode = row[Loc07.staCode],
        staName = row[Loc07.staName],
    )

    suspend fun getLoc07(): List<Loc07Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Loc07.selectAll().map(::resultRowLoc07)
        }
    }

    suspend fun getLoc07ByStaCode(staCode: String): Loc07Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Loc07.selectAll()
                .where { Loc07.staCode eq staCode }
                .singleOrNull()?.let {
                    Loc07Dto(
                        id = it[Loc07.id],
                        routeID = it[Loc07.routeID],
                        staCode = it[Loc07.staCode],
                        fromStaCode = it[Loc07.fromStaCode],
                        toStaCode = it[Loc07.toStaCode],
                        staName = it[Loc07.staName],
                    )
                }
        }
    }
}
