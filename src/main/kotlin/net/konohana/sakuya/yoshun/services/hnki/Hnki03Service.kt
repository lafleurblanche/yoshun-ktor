package net.konohana.sakuya.yoshun.services.hnki

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.hnki.Hnki03Dto
import net.konohana.sakuya.yoshun.setup.models.hnki.Hnki03
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Hnki03Service {
    private fun resultRowHnki03(row: ResultRow) = Hnki03Dto(
        id = row[Hnki03.id],
        routeID = row[Hnki03.routeID],
        fromStaCode = row[Hnki03.fromStaCode],
        toStaCode = row[Hnki03.toStaCode],
        staCode = row[Hnki03.staCode],
        staName = row[Hnki03.staName],
    )

    suspend fun getHnki03(): List<Hnki03Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Hnki03.selectAll().map(::resultRowHnki03)
        }
    }

    suspend fun getHnki03ByStaCode(staCode: String): Hnki03Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Hnki03.selectAll()
                .where { Hnki03.staCode eq staCode }
                .singleOrNull()?.let {
                    Hnki03Dto(
                        id = it[Hnki03.id],
                        routeID = it[Hnki03.routeID],
                        staCode = it[Hnki03.staCode],
                        fromStaCode = it[Hnki03.fromStaCode],
                        toStaCode = it[Hnki03.toStaCode],
                        staName = it[Hnki03.staName],
                    )
                }
        }
    }
}
