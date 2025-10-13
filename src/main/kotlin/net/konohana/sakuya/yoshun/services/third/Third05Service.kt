package net.konohana.sakuya.yoshun.services.third

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.third.Third05Dto
import net.konohana.sakuya.yoshun.models.third.Third05
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Third05Service {
    private fun resultRowThird05(row: ResultRow) = Third05Dto(
        id = row[Third05.id],
        routeID = row[Third05.routeID],
        fromStaCode = row[Third05.fromStaCode],
        toStaCode = row[Third05.toStaCode],
        staCode = row[Third05.staCode],
        staName = row[Third05.staName],
    )

    suspend fun getThird05(): List<Third05Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Third05.selectAll().map(::resultRowThird05)
        }
    }

    suspend fun getThird05ByStaCode(staCode: String): Third05Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Third05.selectAll()
                .where { Third05.staCode eq staCode }
                .singleOrNull()?.let {
                    Third05Dto(
                        id = it[Third05.id],
                        routeID = it[Third05.routeID],
                        staCode = it[Third05.staCode],
                        fromStaCode = it[Third05.fromStaCode],
                        toStaCode = it[Third05.toStaCode],
                        staName = it[Third05.staName],
                    )
                }
        }
    }
}
