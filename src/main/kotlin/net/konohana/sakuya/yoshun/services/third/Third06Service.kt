package net.konohana.sakuya.yoshun.services.third

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.third.Third06Dto
import net.konohana.sakuya.yoshun.models.third.Third06
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Third06Service {
    private fun resultRowThird06(row: ResultRow) = Third06Dto(
        id = row[Third06.id],
        routeID = row[Third06.routeID],
        fromStaCode = row[Third06.fromStaCode],
        toStaCode = row[Third06.toStaCode],
        staCode = row[Third06.staCode],
        staName = row[Third06.staName],
    )

    suspend fun getThird06(): List<Third06Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Third06.selectAll().map(::resultRowThird06)
        }
    }

    suspend fun getThird06ByStaCode(staCode: String): Third06Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Third06.selectAll()
                .where { Third06.staCode eq staCode }
                .singleOrNull()?.let {
                    Third06Dto(
                        id = it[Third06.id],
                        routeID = it[Third06.routeID],
                        staCode = it[Third06.staCode],
                        fromStaCode = it[Third06.fromStaCode],
                        toStaCode = it[Third06.toStaCode],
                        staName = it[Third06.staName],
                    )
                }
        }
    }
}
