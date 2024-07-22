package net.konohana.sakuya.yoshun.services.lilie

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.lilie.Lilie02Dto
import net.konohana.sakuya.yoshun.models.lilie.Lilie02
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Lilie02Service {
    private fun resultRowLilie02(row: ResultRow) = Lilie02Dto(
        id = row[Lilie02.id],
        routeID = row[Lilie02.routeID],
        fromStaCode = row[Lilie02.fromStaCode],
        toStaCode = row[Lilie02.toStaCode],
        staCode = row[Lilie02.staCode],
        staName = row[Lilie02.staName],
    )

    suspend fun getLilie02(): List<Lilie02Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Lilie02.selectAll().map(::resultRowLilie02)
        }
    }

    suspend fun getLilie02ByStaCode(staCode: String): Lilie02Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Lilie02.selectAll()
                .where { Lilie02.staCode eq staCode }
                .singleOrNull()?.let {
                    Lilie02Dto(
                        id = it[Lilie02.id],
                        routeID = it[Lilie02.routeID],
                        staCode = it[Lilie02.staCode],
                        fromStaCode = it[Lilie02.fromStaCode],
                        toStaCode = it[Lilie02.toStaCode],
                        staName = it[Lilie02.staName],
                    )
                }
        }
    }
}
