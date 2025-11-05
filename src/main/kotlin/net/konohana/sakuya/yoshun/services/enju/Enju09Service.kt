package net.konohana.sakuya.yoshun.services.enju

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.enju.Enju09Dto
import net.konohana.sakuya.yoshun.models.enju.Enju09
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Enju09Service {
    private fun resultRowEnju09(row: ResultRow) = Enju09Dto(
        id = row[Enju09.id],
        routeID = row[Enju09.routeID],
        fromStaCode = row[Enju09.fromStaCode],
        toStaCode = row[Enju09.toStaCode],
        staCode = row[Enju09.staCode],
        staName = row[Enju09.staName],
    )

    suspend fun getEnju09(): List<Enju09Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Enju09.selectAll().map(::resultRowEnju09)
        }
    }

    suspend fun getEnju09ByStaCode(staCode: String): Enju09Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Enju09.selectAll()
                .where { Enju09.staCode eq staCode }
                .singleOrNull()?.let {
                    Enju09Dto(
                        id = it[Enju09.id],
                        routeID = it[Enju09.routeID],
                        staCode = it[Enju09.staCode],
                        fromStaCode = it[Enju09.fromStaCode],
                        toStaCode = it[Enju09.toStaCode],
                        staName = it[Enju09.staName],
                    )
                }
        }
    }
}
