package net.konohana.sakuya.yoshun.services.lilie

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.lilie.Lilie01Dto
import net.konohana.sakuya.yoshun.models.lilie.Lilie01
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Lilie01Service {
    private fun resultRowLilie01(row: ResultRow) = Lilie01Dto(
        id = row[Lilie01.id],
        routeID = row[Lilie01.routeID],
        fromStaCode = row[Lilie01.fromStaCode],
        toStaCode = row[Lilie01.toStaCode],
        staCode = row[Lilie01.staCode],
        staName = row[Lilie01.staName],
    )

    suspend fun getLilie01(): List<Lilie01Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Lilie01.selectAll().map(::resultRowLilie01)
        }
    }

    suspend fun getLilie01ByStaCode(staCode: String): Lilie01Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Lilie01.selectAll()
                .where { Lilie01.staCode eq staCode }
                .singleOrNull()?.let {
                    Lilie01Dto(
                        id = it[Lilie01.id],
                        routeID = it[Lilie01.routeID],
                        staCode = it[Lilie01.staCode],
                        fromStaCode = it[Lilie01.fromStaCode],
                        toStaCode = it[Lilie01.toStaCode],
                        staName = it[Lilie01.staName],
                    )
                }
        }
    }
}
