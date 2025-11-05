package net.konohana.sakuya.yoshun.services.enju

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.enju.Enju13Dto
import net.konohana.sakuya.yoshun.models.enju.Enju13
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Enju13Service {
    private fun resultRowEnju13(row: ResultRow) = Enju13Dto(
        id = row[Enju13.id],
        routeID = row[Enju13.routeID],
        fromStaCode = row[Enju13.fromStaCode],
        toStaCode = row[Enju13.toStaCode],
        staCode = row[Enju13.staCode],
        staName = row[Enju13.staName],
    )

    suspend fun getEnju13(): List<Enju13Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Enju13.selectAll().map(::resultRowEnju13)
        }
    }

    suspend fun getEnju13ByStaCode(staCode: String): Enju13Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Enju13.selectAll()
                .where { Enju13.staCode eq staCode }
                .singleOrNull()?.let {
                    Enju13Dto(
                        id = it[Enju13.id],
                        routeID = it[Enju13.routeID],
                        staCode = it[Enju13.staCode],
                        fromStaCode = it[Enju13.fromStaCode],
                        toStaCode = it[Enju13.toStaCode],
                        staName = it[Enju13.staName],
                    )
                }
        }
    }
}
