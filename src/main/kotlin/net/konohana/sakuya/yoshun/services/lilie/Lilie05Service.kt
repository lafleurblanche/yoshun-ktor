package net.konohana.sakuya.yoshun.services.lilie

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.lilie.Lilie05Dto
import net.konohana.sakuya.yoshun.models.lilie.Lilie05
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Lilie05Service {
    private fun resultRowLilie05(row: ResultRow) = Lilie05Dto(
        id = row[Lilie05.id],
        routeID = row[Lilie05.routeID],
        fromStaCode = row[Lilie05.fromStaCode],
        toStaCode = row[Lilie05.toStaCode],
        staCode = row[Lilie05.staCode],
        staName = row[Lilie05.staName],
    )

    suspend fun getLilie05(): List<Lilie05Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Lilie05.selectAll().map(::resultRowLilie05)
        }
    }

    suspend fun getLilie05ByStaCode(staCode: String): Lilie05Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Lilie05.selectAll()
                .where { Lilie05.staCode eq staCode }
                .singleOrNull()?.let {
                    Lilie05Dto(
                        id = it[Lilie05.id],
                        routeID = it[Lilie05.routeID],
                        staCode = it[Lilie05.staCode],
                        fromStaCode = it[Lilie05.fromStaCode],
                        toStaCode = it[Lilie05.toStaCode],
                        staName = it[Lilie05.staName],
                    )
                }
        }
    }
}
