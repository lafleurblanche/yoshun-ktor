package net.konohana.sakuya.yoshun.services.enju

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.enju.Enju06Dto
import net.konohana.sakuya.yoshun.models.enju.Enju06
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Enju06Service {
    private fun resultRowEnju06(row: ResultRow) = Enju06Dto(
        id = row[Enju06.id],
        routeID = row[Enju06.routeID],
        fromStaCode = row[Enju06.fromStaCode],
        toStaCode = row[Enju06.toStaCode],
        staCode = row[Enju06.staCode],
        staName = row[Enju06.staName],
    )

    suspend fun getEnju06(): List<Enju06Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Enju06.selectAll().map(::resultRowEnju06)
        }
    }

    suspend fun getEnju06ByStaCode(staCode: String): Enju06Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Enju06.selectAll()
                .where { Enju06.staCode eq staCode }
                .singleOrNull()?.let {
                    Enju06Dto(
                        id = it[Enju06.id],
                        routeID = it[Enju06.routeID],
                        staCode = it[Enju06.staCode],
                        fromStaCode = it[Enju06.fromStaCode],
                        toStaCode = it[Enju06.toStaCode],
                        staName = it[Enju06.staName],
                    )
                }
        }
    }
}
