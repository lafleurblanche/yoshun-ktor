package net.konohana.sakuya.yoshun.services.enju

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.enju.Enju11Dto
import net.konohana.sakuya.yoshun.models.enju.Enju11
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Enju11Service {
    private fun resultRowEnju11(row: ResultRow) = Enju11Dto(
        id = row[Enju11.id],
        routeID = row[Enju11.routeID],
        fromStaCode = row[Enju11.fromStaCode],
        toStaCode = row[Enju11.toStaCode],
        staCode = row[Enju11.staCode],
        staName = row[Enju11.staName],
    )

    suspend fun getEnju11(): List<Enju11Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Enju11.selectAll().map(::resultRowEnju11)
        }
    }

    suspend fun getEnju11ByStaCode(staCode: String): Enju11Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Enju11.selectAll()
                .where { Enju11.staCode eq staCode }
                .singleOrNull()?.let {
                    Enju11Dto(
                        id = it[Enju11.id],
                        routeID = it[Enju11.routeID],
                        staCode = it[Enju11.staCode],
                        fromStaCode = it[Enju11.fromStaCode],
                        toStaCode = it[Enju11.toStaCode],
                        staName = it[Enju11.staName],
                    )
                }
        }
    }
}
