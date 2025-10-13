package net.konohana.sakuya.yoshun.services.third

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.third.Third03Dto
import net.konohana.sakuya.yoshun.models.third.Third03
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Third03Service {
    private fun resultRowThird03(row: ResultRow) = Third03Dto(
        id = row[Third03.id],
        routeID = row[Third03.routeID],
        fromStaCode = row[Third03.fromStaCode],
        toStaCode = row[Third03.toStaCode],
        staCode = row[Third03.staCode],
        staName = row[Third03.staName],
    )

    suspend fun getThird03(): List<Third03Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Third03.selectAll().map(::resultRowThird03)
        }
    }

    suspend fun getThird03ByStaCode(staCode: String): Third03Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Third03.selectAll()
                .where { Third03.staCode eq staCode }
                .singleOrNull()?.let {
                    Third03Dto(
                        id = it[Third03.id],
                        routeID = it[Third03.routeID],
                        staCode = it[Third03.staCode],
                        fromStaCode = it[Third03.fromStaCode],
                        toStaCode = it[Third03.toStaCode],
                        staName = it[Third03.staName],
                    )
                }
        }
    }
}
