package net.konohana.sakuya.yoshun.services.third

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.third.Third04Dto
import net.konohana.sakuya.yoshun.models.third.Third04
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Third04Service {
    private fun resultRowThird04(row: ResultRow) = Third04Dto(
        id = row[Third04.id],
        routeID = row[Third04.routeID],
        fromStaCode = row[Third04.fromStaCode],
        toStaCode = row[Third04.toStaCode],
        staCode = row[Third04.staCode],
        staName = row[Third04.staName],
    )

    suspend fun getThird04(): List<Third04Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Third04.selectAll().map(::resultRowThird04)
        }
    }

    suspend fun getThird04ByStaCode(staCode: String): Third04Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Third04.selectAll()
                .where { Third04.staCode eq staCode }
                .singleOrNull()?.let {
                    Third04Dto(
                        id = it[Third04.id],
                        routeID = it[Third04.routeID],
                        staCode = it[Third04.staCode],
                        fromStaCode = it[Third04.fromStaCode],
                        toStaCode = it[Third04.toStaCode],
                        staName = it[Third04.staName],
                    )
                }
        }
    }
}
