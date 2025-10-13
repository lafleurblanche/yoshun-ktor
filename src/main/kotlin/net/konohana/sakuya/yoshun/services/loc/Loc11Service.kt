package net.konohana.sakuya.yoshun.services.loc

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.loc.Loc11Dto
import net.konohana.sakuya.yoshun.models.loc.Loc11
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Loc11Service {
    private fun resultRowLoc11(row: ResultRow) = Loc11Dto(
        id = row[Loc11.id],
        routeID = row[Loc11.routeID],
        fromStaCode = row[Loc11.fromStaCode],
        toStaCode = row[Loc11.toStaCode],
        staCode = row[Loc11.staCode],
        staName = row[Loc11.staName],
    )

    suspend fun getLoc11(): List<Loc11Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Loc11.selectAll().map(::resultRowLoc11)
        }
    }

    suspend fun getLoc11ByStaCode(staCode: String): Loc11Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Loc11.selectAll()
                .where { Loc11.staCode eq staCode }
                .singleOrNull()?.let {
                    Loc11Dto(
                        id = it[Loc11.id],
                        routeID = it[Loc11.routeID],
                        staCode = it[Loc11.staCode],
                        fromStaCode = it[Loc11.fromStaCode],
                        toStaCode = it[Loc11.toStaCode],
                        staName = it[Loc11.staName],
                    )
                }
        }
    }
}
