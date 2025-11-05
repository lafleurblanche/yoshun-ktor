package net.konohana.sakuya.yoshun.services.enju

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.enju.Enju16Dto
import net.konohana.sakuya.yoshun.models.enju.Enju16
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Enju16Service {
    private fun resultRowEnju16(row: ResultRow) = Enju16Dto(
        id = row[Enju16.id],
        routeID = row[Enju16.routeID],
        fromStaCode = row[Enju16.fromStaCode],
        toStaCode = row[Enju16.toStaCode],
        staCode = row[Enju16.staCode],
        staName = row[Enju16.staName],
    )

    suspend fun getEnju16(): List<Enju16Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Enju16.selectAll().map(::resultRowEnju16)
        }
    }

    suspend fun getEnju16ByStaCode(staCode: String): Enju16Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Enju16.selectAll()
                .where { Enju16.staCode eq staCode }
                .singleOrNull()?.let {
                    Enju16Dto(
                        id = it[Enju16.id],
                        routeID = it[Enju16.routeID],
                        staCode = it[Enju16.staCode],
                        fromStaCode = it[Enju16.fromStaCode],
                        toStaCode = it[Enju16.toStaCode],
                        staName = it[Enju16.staName],
                    )
                }
        }
    }
}
