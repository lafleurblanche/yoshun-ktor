package net.konohana.sakuya.yoshun.services.loc

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.loc.Loc03Dto
import net.konohana.sakuya.yoshun.models.loc.Loc03
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Loc03Service {
    private fun resultRowLoc03(row: ResultRow) = Loc03Dto(
        id = row[Loc03.id],
        routeID = row[Loc03.routeID],
        fromStaCode = row[Loc03.fromStaCode],
        toStaCode = row[Loc03.toStaCode],
        staCode = row[Loc03.staCode],
        staName = row[Loc03.staName],
    )

    suspend fun getLoc03(): List<Loc03Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Loc03.selectAll().map(::resultRowLoc03)
        }
    }

    suspend fun getLoc03ByStaCode(staCode: String): Loc03Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Loc03.selectAll()
                .where { Loc03.staCode eq staCode }
                .singleOrNull()?.let {
                    Loc03Dto(
                        id = it[Loc03.id],
                        routeID = it[Loc03.routeID],
                        staCode = it[Loc03.staCode],
                        fromStaCode = it[Loc03.fromStaCode],
                        toStaCode = it[Loc03.toStaCode],
                        staName = it[Loc03.staName],
                    )
                }
        }
    }
}
