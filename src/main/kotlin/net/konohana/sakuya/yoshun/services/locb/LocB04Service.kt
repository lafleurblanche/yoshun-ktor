package net.konohana.sakuya.yoshun.services.locb

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.locb.LocB04Dto
import net.konohana.sakuya.yoshun.models.locb.LocB04
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class LocB04Service {
    private fun resultRowLocB04(row: ResultRow) = LocB04Dto(
        id = row[LocB04.id],
        routeID = row[LocB04.routeID],
        fromStaCode = row[LocB04.fromStaCode],
        toStaCode = row[LocB04.toStaCode],
        staCode = row[LocB04.staCode],
        staName = row[LocB04.staName],
    )

    suspend fun getLocB04(): List<LocB04Dto> {
        return KaedeDatabaseFactory.dbQuery {
            LocB04.selectAll().map(::resultRowLocB04)
        }
    }

    suspend fun getLocB04ByStaCode(staCode: String): LocB04Dto? {
        return KaedeDatabaseFactory.dbQuery {
            LocB04.selectAll()
                .where { LocB04.staCode eq staCode }
                .singleOrNull()?.let {
                    LocB04Dto(
                        id = it[LocB04.id],
                        routeID = it[LocB04.routeID],
                        staCode = it[LocB04.staCode],
                        fromStaCode = it[LocB04.fromStaCode],
                        toStaCode = it[LocB04.toStaCode],
                        staName = it[LocB04.staName],
                    )
                }
        }
    }
}
