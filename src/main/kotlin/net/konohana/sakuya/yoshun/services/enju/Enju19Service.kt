package net.konohana.sakuya.yoshun.services.enju

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.enju.Enju19Dto
import net.konohana.sakuya.yoshun.models.enju.Enju19
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Enju19Service {
    private fun resultRowEnju19(row: ResultRow) = Enju19Dto(
        id = row[Enju19.id],
        routeID = row[Enju19.routeID],
        fromStaCode = row[Enju19.fromStaCode],
        toStaCode = row[Enju19.toStaCode],
        staCode = row[Enju19.staCode],
        staName = row[Enju19.staName],
    )

    suspend fun getEnju19(): List<Enju19Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Enju19.selectAll().map(::resultRowEnju19)
        }
    }

    suspend fun getEnju19ByStaCode(staCode: String): Enju19Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Enju19.selectAll()
                .where { Enju19.staCode eq staCode }
                .singleOrNull()?.let {
                    Enju19Dto(
                        id = it[Enju19.id],
                        routeID = it[Enju19.routeID],
                        staCode = it[Enju19.staCode],
                        fromStaCode = it[Enju19.fromStaCode],
                        toStaCode = it[Enju19.toStaCode],
                        staName = it[Enju19.staName],
                    )
                }
        }
    }
}
