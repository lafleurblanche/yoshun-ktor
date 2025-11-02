package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra09Dto
import net.konohana.sakuya.yoshun.models.quadra.Quadra09
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Quadra09Service {
    private fun resultRowQuadra09(row: ResultRow) = Quadra09Dto(
        id = row[Quadra09.id],
        routeID = row[Quadra09.routeID],
        fromStaCode = row[Quadra09.fromStaCode],
        toStaCode = row[Quadra09.toStaCode],
        staCode = row[Quadra09.staCode],
        staName = row[Quadra09.staName],
    )

    suspend fun getQuadra09(): List<Quadra09Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra09.selectAll().map(::resultRowQuadra09)
        }
    }

    suspend fun getQuadra09ByStaCode(staCode: String): Quadra09Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra09.selectAll()
                .where { Quadra09.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra09Dto(
                        id = it[Quadra09.id],
                        routeID = it[Quadra09.routeID],
                        staCode = it[Quadra09.staCode],
                        fromStaCode = it[Quadra09.fromStaCode],
                        toStaCode = it[Quadra09.toStaCode],
                        staName = it[Quadra09.staName],
                    )
                }
        }
    }
}
