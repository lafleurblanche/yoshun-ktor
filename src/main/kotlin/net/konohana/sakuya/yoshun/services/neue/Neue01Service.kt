package net.konohana.sakuya.yoshun.services.neue

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.neue.Neue01Dto
import net.konohana.sakuya.yoshun.models.neue.Neue01
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Neue01Service {
    private fun resultRowNeue01(row: ResultRow) = Neue01Dto(
        id = row[Neue01.id],
        routeID = row[Neue01.routeID],
        fromStaCode = row[Neue01.fromStaCode],
        toStaCode = row[Neue01.toStaCode],
        staCode = row[Neue01.staCode],
        staName = row[Neue01.staName],
    )

    suspend fun getNeue01(): List<Neue01Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Neue01.selectAll().map(::resultRowNeue01)
        }
    }

    suspend fun getNeue01ByStaCode(staCode: String): Neue01Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Neue01.selectAll()
                .where { Neue01.staCode eq staCode }
                .singleOrNull()?.let {
                    Neue01Dto(
                        id = it[Neue01.id],
                        routeID = it[Neue01.routeID],
                        staCode = it[Neue01.staCode],
                        fromStaCode = it[Neue01.fromStaCode],
                        toStaCode = it[Neue01.toStaCode],
                        staName = it[Neue01.staName],
                    )
                }
        }
    }
}
