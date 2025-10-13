package net.konohana.sakuya.yoshun.services.hnki

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.hnki.Hnki01Dto
import net.konohana.sakuya.yoshun.setup.models.hnki.Hnki01
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Hnki01Service {
    private fun resultRowHnki01(row: ResultRow) = Hnki01Dto(
        id = row[Hnki01.id],
        routeID = row[Hnki01.routeID],
        fromStaCode = row[Hnki01.fromStaCode],
        toStaCode = row[Hnki01.toStaCode],
        staCode = row[Hnki01.staCode],
        staName = row[Hnki01.staName],
    )

    suspend fun getHnki01(): List<Hnki01Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Hnki01.selectAll().map(::resultRowHnki01)
        }
    }

    suspend fun getHnki01ByStaCode(staCode: String): Hnki01Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Hnki01.selectAll()
                .where { Hnki01.staCode eq staCode }
                .singleOrNull()?.let {
                    Hnki01Dto(
                        id = it[Hnki01.id],
                        routeID = it[Hnki01.routeID],
                        staCode = it[Hnki01.staCode],
                        fromStaCode = it[Hnki01.fromStaCode],
                        toStaCode = it[Hnki01.toStaCode],
                        staName = it[Hnki01.staName],
                    )
                }
        }
    }
}
