package net.konohana.sakuya.yoshun.services.loc

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.loc.Loc08Dto
import net.konohana.sakuya.yoshun.models.loc.Loc08
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Loc08Service {
    private fun resultRowLoc08(row: ResultRow) = Loc08Dto(
        id = row[Loc08.id],
        routeID = row[Loc08.routeID],
        fromStaCode = row[Loc08.fromStaCode],
        toStaCode = row[Loc08.toStaCode],
        staCode = row[Loc08.staCode],
        staName = row[Loc08.staName],
    )

    suspend fun getLoc08(): List<Loc08Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Loc08.selectAll().map(::resultRowLoc08)
        }
    }

    suspend fun getLoc08ByStaCode(staCode: String): Loc08Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Loc08.selectAll()
                .where { Loc08.staCode eq staCode }
                .singleOrNull()?.let {
                    Loc08Dto(
                        id = it[Loc08.id],
                        routeID = it[Loc08.routeID],
                        staCode = it[Loc08.staCode],
                        fromStaCode = it[Loc08.fromStaCode],
                        toStaCode = it[Loc08.toStaCode],
                        staName = it[Loc08.staName],
                    )
                }
        }
    }
}
