package net.konohana.sakuya.yoshun.services.locb

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.locb.LocB02Dto
import net.konohana.sakuya.yoshun.models.locb.LocB02
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class LocB02Service {
    private fun resultRowLocB02(row: ResultRow) = LocB02Dto(
        id = row[LocB02.id],
        routeID = row[LocB02.routeID],
        fromStaCode = row[LocB02.fromStaCode],
        toStaCode = row[LocB02.toStaCode],
        staCode = row[LocB02.staCode],
        staName = row[LocB02.staName],
    )

    suspend fun getLocB02(): List<LocB02Dto> {
        return KaedeDatabaseFactory.dbQuery {
            LocB02.selectAll().map(::resultRowLocB02)
        }
    }

    suspend fun getLocB02ByStaCode(staCode: String): LocB02Dto? {
        return KaedeDatabaseFactory.dbQuery {
            LocB02.selectAll()
                .where { LocB02.staCode eq staCode }
                .singleOrNull()?.let {
                    LocB02Dto(
                        id = it[LocB02.id],
                        routeID = it[LocB02.routeID],
                        staCode = it[LocB02.staCode],
                        fromStaCode = it[LocB02.fromStaCode],
                        toStaCode = it[LocB02.toStaCode],
                        staName = it[LocB02.staName],
                    )
                }
        }
    }
}
