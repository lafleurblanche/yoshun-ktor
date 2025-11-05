package net.konohana.sakuya.yoshun.services.enju

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.enju.Enju04Dto
import net.konohana.sakuya.yoshun.models.enju.Enju04
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Enju04Service {
    private fun resultRowEnju04(row: ResultRow) = Enju04Dto(
        id = row[Enju04.id],
        routeID = row[Enju04.routeID],
        fromStaCode = row[Enju04.fromStaCode],
        toStaCode = row[Enju04.toStaCode],
        staCode = row[Enju04.staCode],
        staName = row[Enju04.staName],
    )

    suspend fun getEnju04(): List<Enju04Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Enju04.selectAll().map(::resultRowEnju04)
        }
    }

    suspend fun getEnju04ByStaCode(staCode: String): Enju04Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Enju04.selectAll()
                .where { Enju04.staCode eq staCode }
                .singleOrNull()?.let {
                    Enju04Dto(
                        id = it[Enju04.id],
                        routeID = it[Enju04.routeID],
                        staCode = it[Enju04.staCode],
                        fromStaCode = it[Enju04.fromStaCode],
                        toStaCode = it[Enju04.toStaCode],
                        staName = it[Enju04.staName],
                    )
                }
        }
    }
}
