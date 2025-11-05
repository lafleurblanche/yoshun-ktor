package net.konohana.sakuya.yoshun.services.enju

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.enju.Enju03Dto
import net.konohana.sakuya.yoshun.models.enju.Enju03
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Enju03Service {
    private fun resultRowEnju03(row: ResultRow) = Enju03Dto(
        id = row[Enju03.id],
        routeID = row[Enju03.routeID],
        fromStaCode = row[Enju03.fromStaCode],
        toStaCode = row[Enju03.toStaCode],
        staCode = row[Enju03.staCode],
        staName = row[Enju03.staName],
    )

    suspend fun getEnju03(): List<Enju03Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Enju03.selectAll().map(::resultRowEnju03)
        }
    }

    suspend fun getEnju03ByStaCode(staCode: String): Enju03Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Enju03.selectAll()
                .where { Enju03.staCode eq staCode }
                .singleOrNull()?.let {
                    Enju03Dto(
                        id = it[Enju03.id],
                        routeID = it[Enju03.routeID],
                        staCode = it[Enju03.staCode],
                        fromStaCode = it[Enju03.fromStaCode],
                        toStaCode = it[Enju03.toStaCode],
                        staName = it[Enju03.staName],
                    )
                }
        }
    }
}
