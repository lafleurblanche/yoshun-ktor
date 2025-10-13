package net.konohana.sakuya.yoshun.services.third

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.third.Third02Dto
import net.konohana.sakuya.yoshun.models.third.Third02
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Third02Service {
    private fun resultRowThird02(row: ResultRow) = Third02Dto(
        id = row[Third02.id],
        routeID = row[Third02.routeID],
        fromStaCode = row[Third02.fromStaCode],
        toStaCode = row[Third02.toStaCode],
        staCode = row[Third02.staCode],
        staName = row[Third02.staName],
    )

    suspend fun getThird02(): List<Third02Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Third02.selectAll().map(::resultRowThird02)
        }
    }

    suspend fun getThird02ByStaCode(staCode: String): Third02Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Third02.selectAll()
                .where { Third02.staCode eq staCode }
                .singleOrNull()?.let {
                    Third02Dto(
                        id = it[Third02.id],
                        routeID = it[Third02.routeID],
                        staCode = it[Third02.staCode],
                        fromStaCode = it[Third02.fromStaCode],
                        toStaCode = it[Third02.toStaCode],
                        staName = it[Third02.staName],
                    )
                }
        }
    }
}
