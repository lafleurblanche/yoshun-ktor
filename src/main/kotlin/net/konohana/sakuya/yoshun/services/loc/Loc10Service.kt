package net.konohana.sakuya.yoshun.services.loc

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.loc.Loc10Dto
import net.konohana.sakuya.yoshun.models.loc.Loc10
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Loc10Service {
    private fun resultRowLoc10(row: ResultRow) = Loc10Dto(
        id = row[Loc10.id],
        routeID = row[Loc10.routeID],
        fromStaCode = row[Loc10.fromStaCode],
        toStaCode = row[Loc10.toStaCode],
        staCode = row[Loc10.staCode],
        staName = row[Loc10.staName],
    )

    suspend fun getLoc10(): List<Loc10Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Loc10.selectAll().map(::resultRowLoc10)
        }
    }

    suspend fun getLoc10ByStaCode(staCode: String): Loc10Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Loc10.selectAll()
                .where { Loc10.staCode eq staCode }
                .singleOrNull()?.let {
                    Loc10Dto(
                        id = it[Loc10.id],
                        routeID = it[Loc10.routeID],
                        staCode = it[Loc10.staCode],
                        fromStaCode = it[Loc10.fromStaCode],
                        toStaCode = it[Loc10.toStaCode],
                        staName = it[Loc10.staName],
                    )
                }
        }
    }
}
