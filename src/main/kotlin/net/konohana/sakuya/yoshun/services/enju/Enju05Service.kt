package net.konohana.sakuya.yoshun.services.enju

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.enju.Enju05Dto
import net.konohana.sakuya.yoshun.models.enju.Enju05
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Enju05Service {
    private fun resultRowEnju05(row: ResultRow) = Enju05Dto(
        id = row[Enju05.id],
        routeID = row[Enju05.routeID],
        fromStaCode = row[Enju05.fromStaCode],
        toStaCode = row[Enju05.toStaCode],
        staCode = row[Enju05.staCode],
        staName = row[Enju05.staName],
    )

    suspend fun getEnju05(): List<Enju05Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Enju05.selectAll().map(::resultRowEnju05)
        }
    }

    suspend fun getEnju05ByStaCode(staCode: String): Enju05Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Enju05.selectAll()
                .where { Enju05.staCode eq staCode }
                .singleOrNull()?.let {
                    Enju05Dto(
                        id = it[Enju05.id],
                        routeID = it[Enju05.routeID],
                        staCode = it[Enju05.staCode],
                        fromStaCode = it[Enju05.fromStaCode],
                        toStaCode = it[Enju05.toStaCode],
                        staName = it[Enju05.staName],
                    )
                }
        }
    }
}
