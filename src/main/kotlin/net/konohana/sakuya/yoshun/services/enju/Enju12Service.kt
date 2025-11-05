package net.konohana.sakuya.yoshun.services.enju

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.enju.Enju12Dto
import net.konohana.sakuya.yoshun.models.enju.Enju12
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Enju12Service {
    private fun resultRowEnju12(row: ResultRow) = Enju12Dto(
        id = row[Enju12.id],
        routeID = row[Enju12.routeID],
        fromStaCode = row[Enju12.fromStaCode],
        toStaCode = row[Enju12.toStaCode],
        staCode = row[Enju12.staCode],
        staName = row[Enju12.staName],
    )

    suspend fun getEnju12(): List<Enju12Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Enju12.selectAll().map(::resultRowEnju12)
        }
    }

    suspend fun getEnju12ByStaCode(staCode: String): Enju12Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Enju12.selectAll()
                .where { Enju12.staCode eq staCode }
                .singleOrNull()?.let {
                    Enju12Dto(
                        id = it[Enju12.id],
                        routeID = it[Enju12.routeID],
                        staCode = it[Enju12.staCode],
                        fromStaCode = it[Enju12.fromStaCode],
                        toStaCode = it[Enju12.toStaCode],
                        staName = it[Enju12.staName],
                    )
                }
        }
    }
}
