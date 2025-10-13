package net.konohana.sakuya.yoshun.services.third

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.third.Third07Dto
import net.konohana.sakuya.yoshun.models.third.Third07
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Third07Service {
    private fun resultRowThird07(row: ResultRow) = Third07Dto(
        id = row[Third07.id],
        routeID = row[Third07.routeID],
        fromStaCode = row[Third07.fromStaCode],
        toStaCode = row[Third07.toStaCode],
        staCode = row[Third07.staCode],
        staName = row[Third07.staName],
    )

    suspend fun getThird07(): List<Third07Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Third07.selectAll().map(::resultRowThird07)
        }
    }

    suspend fun getThird07ByStaCode(staCode: String): Third07Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Third07.selectAll()
                .where { Third07.staCode eq staCode }
                .singleOrNull()?.let {
                    Third07Dto(
                        id = it[Third07.id],
                        routeID = it[Third07.routeID],
                        staCode = it[Third07.staCode],
                        fromStaCode = it[Third07.fromStaCode],
                        toStaCode = it[Third07.toStaCode],
                        staName = it[Third07.staName],
                    )
                }
        }
    }
}
