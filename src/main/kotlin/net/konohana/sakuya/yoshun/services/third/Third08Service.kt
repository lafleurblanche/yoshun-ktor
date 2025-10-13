package net.konohana.sakuya.yoshun.services.third

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.third.Third08Dto
import net.konohana.sakuya.yoshun.models.third.Third08
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Third08Service {
    private fun resultRowThird08(row: ResultRow) = Third08Dto(
        id = row[Third08.id],
        routeID = row[Third08.routeID],
        fromStaCode = row[Third08.fromStaCode],
        toStaCode = row[Third08.toStaCode],
        staCode = row[Third08.staCode],
        staName = row[Third08.staName],
    )

    suspend fun getThird08(): List<Third08Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Third08.selectAll().map(::resultRowThird08)
        }
    }

    suspend fun getThird08ByStaCode(staCode: String): Third08Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Third08.selectAll()
                .where { Third08.staCode eq staCode }
                .singleOrNull()?.let {
                    Third08Dto(
                        id = it[Third08.id],
                        routeID = it[Third08.routeID],
                        staCode = it[Third08.staCode],
                        fromStaCode = it[Third08.fromStaCode],
                        toStaCode = it[Third08.toStaCode],
                        staName = it[Third08.staName],
                    )
                }
        }
    }
}
