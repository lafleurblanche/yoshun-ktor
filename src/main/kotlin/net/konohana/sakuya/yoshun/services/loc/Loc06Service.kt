package net.konohana.sakuya.yoshun.services.loc

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.loc.Loc06Dto
import net.konohana.sakuya.yoshun.models.loc.Loc06
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Loc06Service {
    private fun resultRowLoc06(row: ResultRow) = Loc06Dto(
        id = row[Loc06.id],
        routeID = row[Loc06.routeID],
        fromStaCode = row[Loc06.fromStaCode],
        toStaCode = row[Loc06.toStaCode],
        staCode = row[Loc06.staCode],
        staName = row[Loc06.staName],
    )

    suspend fun getLoc06(): List<Loc06Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Loc06.selectAll().map(::resultRowLoc06)
        }
    }

    suspend fun getLoc06ByStaCode(staCode: String): Loc06Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Loc06.selectAll()
                .where { Loc06.staCode eq staCode }
                .singleOrNull()?.let {
                    Loc06Dto(
                        id = it[Loc06.id],
                        routeID = it[Loc06.routeID],
                        staCode = it[Loc06.staCode],
                        fromStaCode = it[Loc06.fromStaCode],
                        toStaCode = it[Loc06.toStaCode],
                        staName = it[Loc06.staName],
                    )
                }
        }
    }
}
