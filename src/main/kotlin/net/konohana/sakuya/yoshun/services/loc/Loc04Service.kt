package net.konohana.sakuya.yoshun.services.loc

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.loc.Loc04Dto
import net.konohana.sakuya.yoshun.models.loc.Loc04
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Loc04Service {
    private fun resultRowLoc04(row: ResultRow) = Loc04Dto(
        id = row[Loc04.id],
        routeID = row[Loc04.routeID],
        fromStaCode = row[Loc04.fromStaCode],
        toStaCode = row[Loc04.toStaCode],
        staCode = row[Loc04.staCode],
        staName = row[Loc04.staName],
    )

    suspend fun getLoc04(): List<Loc04Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Loc04.selectAll().map(::resultRowLoc04)
        }
    }

    suspend fun getLoc04ByStaCode(staCode: String): Loc04Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Loc04.selectAll()
                .where { Loc04.staCode eq staCode }
                .singleOrNull()?.let {
                    Loc04Dto(
                        id = it[Loc04.id],
                        routeID = it[Loc04.routeID],
                        staCode = it[Loc04.staCode],
                        fromStaCode = it[Loc04.fromStaCode],
                        toStaCode = it[Loc04.toStaCode],
                        staName = it[Loc04.staName],
                    )
                }
        }
    }
}
