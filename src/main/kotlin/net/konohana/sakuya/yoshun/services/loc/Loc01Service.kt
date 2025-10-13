package net.konohana.sakuya.yoshun.services.loc

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.loc.Loc01Dto
import net.konohana.sakuya.yoshun.models.loc.Loc01
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Loc01Service {
    private fun resultRowLoc01(row: ResultRow) = Loc01Dto(
        id = row[Loc01.id],
        routeID = row[Loc01.routeID],
        fromStaCode = row[Loc01.fromStaCode],
        toStaCode = row[Loc01.toStaCode],
        staCode = row[Loc01.staCode],
        staName = row[Loc01.staName],
    )

    suspend fun getLoc01(): List<Loc01Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Loc01.selectAll().map(::resultRowLoc01)
        }
    }

    suspend fun getLoc01ByStaCode(staCode: String): Loc01Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Loc01.selectAll()
                .where { Loc01.staCode eq staCode }
                .singleOrNull()?.let {
                    Loc01Dto(
                        id = it[Loc01.id],
                        routeID = it[Loc01.routeID],
                        staCode = it[Loc01.staCode],
                        fromStaCode = it[Loc01.fromStaCode],
                        toStaCode = it[Loc01.toStaCode],
                        staName = it[Loc01.staName],
                    )
                }
        }
    }
}
