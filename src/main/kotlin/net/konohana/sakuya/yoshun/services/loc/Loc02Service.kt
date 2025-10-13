package net.konohana.sakuya.yoshun.services.loc

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.loc.Loc02Dto
import net.konohana.sakuya.yoshun.models.loc.Loc02
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Loc02Service {
    private fun resultRowLoc02(row: ResultRow) = Loc02Dto(
        id = row[Loc02.id],
        routeID = row[Loc02.routeID],
        fromStaCode = row[Loc02.fromStaCode],
        toStaCode = row[Loc02.toStaCode],
        staCode = row[Loc02.staCode],
        staName = row[Loc02.staName],
    )

    suspend fun getLoc02(): List<Loc02Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Loc02.selectAll().map(::resultRowLoc02)
        }
    }

    suspend fun getLoc02ByStaCode(staCode: String): Loc02Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Loc02.selectAll()
                .where { Loc02.staCode eq staCode }
                .singleOrNull()?.let {
                    Loc02Dto(
                        id = it[Loc02.id],
                        routeID = it[Loc02.routeID],
                        staCode = it[Loc02.staCode],
                        fromStaCode = it[Loc02.fromStaCode],
                        toStaCode = it[Loc02.toStaCode],
                        staName = it[Loc02.staName],
                    )
                }
        }
    }
}
