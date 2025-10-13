package net.konohana.sakuya.yoshun.services.loc

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.loc.Loc09Dto
import net.konohana.sakuya.yoshun.models.loc.Loc09
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Loc09Service {
    private fun resultRowLoc09(row: ResultRow) = Loc09Dto(
        id = row[Loc09.id],
        routeID = row[Loc09.routeID],
        fromStaCode = row[Loc09.fromStaCode],
        toStaCode = row[Loc09.toStaCode],
        staCode = row[Loc09.staCode],
        staName = row[Loc09.staName],
    )

    suspend fun getLoc09(): List<Loc09Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Loc09.selectAll().map(::resultRowLoc09)
        }
    }

    suspend fun getLoc09ByStaCode(staCode: String): Loc09Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Loc09.selectAll()
                .where { Loc09.staCode eq staCode }
                .singleOrNull()?.let {
                    Loc09Dto(
                        id = it[Loc09.id],
                        routeID = it[Loc09.routeID],
                        staCode = it[Loc09.staCode],
                        fromStaCode = it[Loc09.fromStaCode],
                        toStaCode = it[Loc09.toStaCode],
                        staName = it[Loc09.staName],
                    )
                }
        }
    }
}
