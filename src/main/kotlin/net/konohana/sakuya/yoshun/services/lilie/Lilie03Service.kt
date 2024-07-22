package net.konohana.sakuya.yoshun.services.lilie

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.lilie.Lilie03Dto
import net.konohana.sakuya.yoshun.models.lilie.Lilie03
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Lilie03Service {
    private fun resultRowLilie03(row: ResultRow) = Lilie03Dto(
        id = row[Lilie03.id],
        routeID = row[Lilie03.routeID],
        fromStaCode = row[Lilie03.fromStaCode],
        toStaCode = row[Lilie03.toStaCode],
        staCode = row[Lilie03.staCode],
        staName = row[Lilie03.staName],
    )

    suspend fun getLilie03(): List<Lilie03Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Lilie03.selectAll().map(::resultRowLilie03)
        }
    }

    suspend fun getLilie03ByStaCode(staCode: String): Lilie03Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Lilie03.selectAll()
                .where { Lilie03.staCode eq staCode }
                .singleOrNull()?.let {
                    Lilie03Dto(
                        id = it[Lilie03.id],
                        routeID = it[Lilie03.routeID],
                        staCode = it[Lilie03.staCode],
                        fromStaCode = it[Lilie03.fromStaCode],
                        toStaCode = it[Lilie03.toStaCode],
                        staName = it[Lilie03.staName],
                    )
                }
        }
    }
}
