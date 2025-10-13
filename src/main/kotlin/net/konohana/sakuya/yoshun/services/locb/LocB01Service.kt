package net.konohana.sakuya.yoshun.services.locb

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.locb.LocB01Dto
import net.konohana.sakuya.yoshun.models.locb.LocB01
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class LocB01Service {
    private fun resultRowLocB01(row: ResultRow) = LocB01Dto(
        id = row[LocB01.id],
        routeID = row[LocB01.routeID],
        fromStaCode = row[LocB01.fromStaCode],
        toStaCode = row[LocB01.toStaCode],
        staCode = row[LocB01.staCode],
        staName = row[LocB01.staName],
    )

    suspend fun getLocB01(): List<LocB01Dto> {
        return KaedeDatabaseFactory.dbQuery {
            LocB01.selectAll().map(::resultRowLocB01)
        }
    }

    suspend fun getLocB01ByStaCode(staCode: String): LocB01Dto? {
        return KaedeDatabaseFactory.dbQuery {
            LocB01.selectAll()
                .where { LocB01.staCode eq staCode }
                .singleOrNull()?.let {
                    LocB01Dto(
                        id = it[LocB01.id],
                        routeID = it[LocB01.routeID],
                        staCode = it[LocB01.staCode],
                        fromStaCode = it[LocB01.fromStaCode],
                        toStaCode = it[LocB01.toStaCode],
                        staName = it[LocB01.staName],
                    )
                }
        }
    }
}
