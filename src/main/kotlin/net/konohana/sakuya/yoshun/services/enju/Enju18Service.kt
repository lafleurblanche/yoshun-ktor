package net.konohana.sakuya.yoshun.services.enju

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.enju.Enju18Dto
import net.konohana.sakuya.yoshun.models.enju.Enju18
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Enju18Service {
    private fun resultRowEnju18(row: ResultRow) = Enju18Dto(
        id = row[Enju18.id],
        routeID = row[Enju18.routeID],
        fromStaCode = row[Enju18.fromStaCode],
        toStaCode = row[Enju18.toStaCode],
        staCode = row[Enju18.staCode],
        staName = row[Enju18.staName],
    )

    suspend fun getEnju18(): List<Enju18Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Enju18.selectAll().map(::resultRowEnju18)
        }
    }

    suspend fun getEnju18ByStaCode(staCode: String): Enju18Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Enju18.selectAll()
                .where { Enju18.staCode eq staCode }
                .singleOrNull()?.let {
                    Enju18Dto(
                        id = it[Enju18.id],
                        routeID = it[Enju18.routeID],
                        staCode = it[Enju18.staCode],
                        fromStaCode = it[Enju18.fromStaCode],
                        toStaCode = it[Enju18.toStaCode],
                        staName = it[Enju18.staName],
                    )
                }
        }
    }
}
