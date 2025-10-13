package net.konohana.sakuya.yoshun.services.locb

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.locb.LocB03Dto
import net.konohana.sakuya.yoshun.models.locb.LocB03
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class LocB03Service {
    private fun resultRowLocB03(row: ResultRow) = LocB03Dto(
        id = row[LocB03.id],
        routeID = row[LocB03.routeID],
        fromStaCode = row[LocB03.fromStaCode],
        toStaCode = row[LocB03.toStaCode],
        staCode = row[LocB03.staCode],
        staName = row[LocB03.staName],
    )

    suspend fun getLocB03(): List<LocB03Dto> {
        return KaedeDatabaseFactory.dbQuery {
            LocB03.selectAll().map(::resultRowLocB03)
        }
    }

    suspend fun getLocB03ByStaCode(staCode: String): LocB03Dto? {
        return KaedeDatabaseFactory.dbQuery {
            LocB03.selectAll()
                .where { LocB03.staCode eq staCode }
                .singleOrNull()?.let {
                    LocB03Dto(
                        id = it[LocB03.id],
                        routeID = it[LocB03.routeID],
                        staCode = it[LocB03.staCode],
                        fromStaCode = it[LocB03.fromStaCode],
                        toStaCode = it[LocB03.toStaCode],
                        staName = it[LocB03.staName],
                    )
                }
        }
    }
}
