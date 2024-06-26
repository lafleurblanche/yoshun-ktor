package net.konohana.sakuya.yoshun.services.neue

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.neue.Neue04Dto
import net.konohana.sakuya.yoshun.models.neue.Neue04
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Neue04Service {
    private fun resultRowNeue04(row: ResultRow) = Neue04Dto(
        id = row[Neue04.id],
        routeID = row[Neue04.routeID],
        fromStaCode = row[Neue04.fromStaCode],
        toStaCode = row[Neue04.toStaCode],
        staCode = row[Neue04.staCode],
        staName = row[Neue04.staName],
    )

    suspend fun getNeue04(): List<Neue04Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Neue04.selectAll().map(::resultRowNeue04)
        }
    }

    suspend fun getNeue04ByStaCode(staCode: String): Neue04Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Neue04.selectAll()
                .where { Neue04.staCode eq staCode }
                .singleOrNull()?.let {
                    Neue04Dto(
                        id = it[Neue04.id],
                        routeID = it[Neue04.routeID],
                        staCode = it[Neue04.staCode],
                        fromStaCode = it[Neue04.fromStaCode],
                        toStaCode = it[Neue04.toStaCode],
                        staName = it[Neue04.staName],
                    )
                }
        }
    }
}
