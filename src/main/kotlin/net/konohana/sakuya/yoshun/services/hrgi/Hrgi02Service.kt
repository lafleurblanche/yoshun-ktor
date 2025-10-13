package net.konohana.sakuya.yoshun.services.hrgi

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.hrgi.Hrgi02Dto
import net.konohana.sakuya.yoshun.models.hrgi.Hrgi02
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Hrgi02Service {
    private fun resultRowHrgi02(row: ResultRow) = Hrgi02Dto(
        id = row[Hrgi02.id],
        routeID = row[Hrgi02.routeID],
        fromStaCode = row[Hrgi02.fromStaCode],
        toStaCode = row[Hrgi02.toStaCode],
        staCode = row[Hrgi02.staCode],
        staName = row[Hrgi02.staName],
    )

    suspend fun getHrgi02(): List<Hrgi02Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Hrgi02.selectAll().map(::resultRowHrgi02)
        }
    }

    suspend fun getHrgi02ByStaCode(staCode: String): Hrgi02Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Hrgi02.selectAll()
                .where { Hrgi02.staCode eq staCode }
                .singleOrNull()?.let {
                    Hrgi02Dto(
                        id = it[Hrgi02.id],
                        routeID = it[Hrgi02.routeID],
                        staCode = it[Hrgi02.staCode],
                        fromStaCode = it[Hrgi02.fromStaCode],
                        toStaCode = it[Hrgi02.toStaCode],
                        staName = it[Hrgi02.staName],
                    )
                }
        }
    }
}
