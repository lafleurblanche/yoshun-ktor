package net.konohana.sakuya.yoshun.services.loc

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.loc.Loc05Dto
import net.konohana.sakuya.yoshun.models.loc.Loc05
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Loc05Service {
    private fun resultRowLoc05(row: ResultRow) = Loc05Dto(
        id = row[Loc05.id],
        routeID = row[Loc05.routeID],
        fromStaCode = row[Loc05.fromStaCode],
        toStaCode = row[Loc05.toStaCode],
        staCode = row[Loc05.staCode],
        staName = row[Loc05.staName],
    )

    suspend fun getLoc05(): List<Loc05Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Loc05.selectAll().map(::resultRowLoc05)
        }
    }

    suspend fun getLoc05ByStaCode(staCode: String): Loc05Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Loc05.selectAll()
                .where { Loc05.staCode eq staCode }
                .singleOrNull()?.let {
                    Loc05Dto(
                        id = it[Loc05.id],
                        routeID = it[Loc05.routeID],
                        staCode = it[Loc05.staCode],
                        fromStaCode = it[Loc05.fromStaCode],
                        toStaCode = it[Loc05.toStaCode],
                        staName = it[Loc05.staName],
                    )
                }
        }
    }
}
