package net.konohana.sakuya.yoshun.services.third

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.third.Third10Dto
import net.konohana.sakuya.yoshun.models.third.Third10
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Third10Service {
    private fun resultRowThird10(row: ResultRow) = Third10Dto(
        id = row[Third10.id],
        routeID = row[Third10.routeID],
        fromStaCode = row[Third10.fromStaCode],
        toStaCode = row[Third10.toStaCode],
        staCode = row[Third10.staCode],
        staName = row[Third10.staName],
    )

    suspend fun getThird10(): List<Third10Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Third10.selectAll().map(::resultRowThird10)
        }
    }

    suspend fun getThird10ByStaCode(staCode: String): Third10Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Third10.selectAll()
                .where { Third10.staCode eq staCode }
                .singleOrNull()?.let {
                    Third10Dto(
                        id = it[Third10.id],
                        routeID = it[Third10.routeID],
                        staCode = it[Third10.staCode],
                        fromStaCode = it[Third10.fromStaCode],
                        toStaCode = it[Third10.toStaCode],
                        staName = it[Third10.staName],
                    )
                }
        }
    }
}
