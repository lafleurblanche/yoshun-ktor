package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra02Dto
import net.konohana.sakuya.yoshun.models.quadra.Quadra02
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Quadra02Service {
    private fun resultRowQuadra02(row: ResultRow) = Quadra02Dto(
        id = row[Quadra02.id],
        routeID = row[Quadra02.routeID],
        fromStaCode = row[Quadra02.fromStaCode],
        toStaCode = row[Quadra02.toStaCode],
        staCode = row[Quadra02.staCode],
        staName = row[Quadra02.staName],
    )

    suspend fun getQuadra02(): List<Quadra02Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra02.selectAll().map(::resultRowQuadra02)
        }
    }

    suspend fun getQuadra02ByStaCode(staCode: String): Quadra02Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra02.selectAll()
                .where { Quadra02.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra02Dto(
                        id = it[Quadra02.id],
                        routeID = it[Quadra02.routeID],
                        staCode = it[Quadra02.staCode],
                        fromStaCode = it[Quadra02.fromStaCode],
                        toStaCode = it[Quadra02.toStaCode],
                        staName = it[Quadra02.staName],
                    )
                }
        }
    }
}
