package net.konohana.sakuya.yoshun.services.neue

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.neue.Neue02Dto
import net.konohana.sakuya.yoshun.models.neue.Neue02
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Neue02Service {
    private fun resultRowNeue02(row: ResultRow) = Neue02Dto(
        id = row[Neue02.id],
        routeID = row[Neue02.routeID],
        fromStaCode = row[Neue02.fromStaCode],
        toStaCode = row[Neue02.toStaCode],
        staCode = row[Neue02.staCode],
        staName = row[Neue02.staName],
    )

    suspend fun getNeue02(): List<Neue02Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Neue02.selectAll().map(::resultRowNeue02)
        }
    }

    suspend fun getNeue02ByStaCode(staCode: String): Neue02Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Neue02.selectAll()
                .where { Neue02.staCode eq staCode }
                .singleOrNull()?.let {
                    Neue02Dto(
                        id = it[Neue02.id],
                        routeID = it[Neue02.routeID],
                        staCode = it[Neue02.staCode],
                        fromStaCode = it[Neue02.fromStaCode],
                        toStaCode = it[Neue02.toStaCode],
                        staName = it[Neue02.staName],
                    )
                }
        }
    }
}
