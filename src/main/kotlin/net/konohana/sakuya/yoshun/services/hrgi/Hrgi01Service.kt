package net.konohana.sakuya.yoshun.services.hrgi

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.hrgi.Hrgi01Dto
import net.konohana.sakuya.yoshun.models.hrgi.Hrgi01
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Hrgi01Service {
    private fun resultRowHrgi01(row: ResultRow) = Hrgi01Dto(
        id = row[Hrgi01.id],
        routeID = row[Hrgi01.routeID],
        fromStaCode = row[Hrgi01.fromStaCode],
        toStaCode = row[Hrgi01.toStaCode],
        staCode = row[Hrgi01.staCode],
        staName = row[Hrgi01.staName],
    )

    suspend fun getHrgi01(): List<Hrgi01Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Hrgi01.selectAll().map(::resultRowHrgi01)
        }
    }

    suspend fun getHrgi01ByStaCode(staCode: String): Hrgi01Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Hrgi01.selectAll()
                .where { Hrgi01.staCode eq staCode }
                .singleOrNull()?.let {
                    Hrgi01Dto(
                        id = it[Hrgi01.id],
                        routeID = it[Hrgi01.routeID],
                        staCode = it[Hrgi01.staCode],
                        fromStaCode = it[Hrgi01.fromStaCode],
                        toStaCode = it[Hrgi01.toStaCode],
                        staName = it[Hrgi01.staName],
                    )
                }
        }
    }
}
