package net.konohana.sakuya.yoshun.services.third

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.third.Third09Dto
import net.konohana.sakuya.yoshun.models.third.Third09
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Third09Service {
    private fun resultRowThird09(row: ResultRow) = Third09Dto(
        id = row[Third09.id],
        routeID = row[Third09.routeID],
        fromStaCode = row[Third09.fromStaCode],
        toStaCode = row[Third09.toStaCode],
        staCode = row[Third09.staCode],
        staName = row[Third09.staName],
    )

    suspend fun getThird09(): List<Third09Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Third09.selectAll().map(::resultRowThird09)
        }
    }

    suspend fun getThird09ByStaCode(staCode: String): Third09Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Third09.selectAll()
                .where { Third09.staCode eq staCode }
                .singleOrNull()?.let {
                    Third09Dto(
                        id = it[Third09.id],
                        routeID = it[Third09.routeID],
                        staCode = it[Third09.staCode],
                        fromStaCode = it[Third09.fromStaCode],
                        toStaCode = it[Third09.toStaCode],
                        staName = it[Third09.staName],
                    )
                }
        }
    }
}
