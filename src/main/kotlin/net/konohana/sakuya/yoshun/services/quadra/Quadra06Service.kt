package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra06Dto
import net.konohana.sakuya.yoshun.models.quadra.Quadra06
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Quadra06Service {
    private fun resultRowQuadra06(row: ResultRow) = Quadra06Dto(
        id = row[Quadra06.id],
        routeID = row[Quadra06.routeID],
        fromStaCode = row[Quadra06.fromStaCode],
        toStaCode = row[Quadra06.toStaCode],
        staCode = row[Quadra06.staCode],
        staName = row[Quadra06.staName],
    )

    suspend fun getQuadra06(): List<Quadra06Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra06.selectAll().map(::resultRowQuadra06)
        }
    }

    suspend fun getQuadra06ByStaCode(staCode: String): Quadra06Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra06.selectAll()
                .where { Quadra06.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra06Dto(
                        id = it[Quadra06.id],
                        routeID = it[Quadra06.routeID],
                        staCode = it[Quadra06.staCode],
                        fromStaCode = it[Quadra06.fromStaCode],
                        toStaCode = it[Quadra06.toStaCode],
                        staName = it[Quadra06.staName],
                    )
                }
        }
    }
}
