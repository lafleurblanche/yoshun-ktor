package net.konohana.sakuya.yoshun.services.third

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.third.Third01Dto
import net.konohana.sakuya.yoshun.models.third.Third01
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Third01Service {
    private fun resultRowThird01(row: ResultRow) = Third01Dto(
        id = row[Third01.id],
        routeID = row[Third01.routeID],
        fromStaCode = row[Third01.fromStaCode],
        toStaCode = row[Third01.toStaCode],
        staCode = row[Third01.staCode],
        staName = row[Third01.staName],
    )

    suspend fun getThird01(): List<Third01Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Third01.selectAll().map(::resultRowThird01)
        }
    }

    suspend fun getThird01ByStaCode(staCode: String): Third01Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Third01.selectAll()
                .where { Third01.staCode eq staCode }
                .singleOrNull()?.let {
                    Third01Dto(
                        id = it[Third01.id],
                        routeID = it[Third01.routeID],
                        staCode = it[Third01.staCode],
                        fromStaCode = it[Third01.fromStaCode],
                        toStaCode = it[Third01.toStaCode],
                        staName = it[Third01.staName],
                    )
                }
        }
    }
}
