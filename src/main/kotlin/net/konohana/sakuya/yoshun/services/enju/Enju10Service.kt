package net.konohana.sakuya.yoshun.services.enju

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.enju.Enju10Dto
import net.konohana.sakuya.yoshun.models.enju.Enju10
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Enju10Service {
    private fun resultRowEnju10(row: ResultRow) = Enju10Dto(
        id = row[Enju10.id],
        routeID = row[Enju10.routeID],
        fromStaCode = row[Enju10.fromStaCode],
        toStaCode = row[Enju10.toStaCode],
        staCode = row[Enju10.staCode],
        staName = row[Enju10.staName],
    )

    suspend fun getEnju10(): List<Enju10Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Enju10.selectAll().map(::resultRowEnju10)
        }
    }

    suspend fun getEnju10ByStaCode(staCode: String): Enju10Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Enju10.selectAll()
                .where { Enju10.staCode eq staCode }
                .singleOrNull()?.let {
                    Enju10Dto(
                        id = it[Enju10.id],
                        routeID = it[Enju10.routeID],
                        staCode = it[Enju10.staCode],
                        fromStaCode = it[Enju10.fromStaCode],
                        toStaCode = it[Enju10.toStaCode],
                        staName = it[Enju10.staName],
                    )
                }
        }
    }
}
