package net.konohana.sakuya.yoshun.services.hnki

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.hnki.Hnki02Dto
import net.konohana.sakuya.yoshun.setup.models.hnki.Hnki02
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Hnki02Service {
    private fun resultRowHnki02(row: ResultRow) = Hnki02Dto(
        id = row[Hnki02.id],
        routeID = row[Hnki02.routeID],
        fromStaCode = row[Hnki02.fromStaCode],
        toStaCode = row[Hnki02.toStaCode],
        staCode = row[Hnki02.staCode],
        staName = row[Hnki02.staName],
    )

    suspend fun getHnki02(): List<Hnki02Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Hnki02.selectAll().map(::resultRowHnki02)
        }
    }

    suspend fun getHnki02ByStaCode(staCode: String): Hnki02Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Hnki02.selectAll()
                .where { Hnki02.staCode eq staCode }
                .singleOrNull()?.let {
                    Hnki02Dto(
                        id = it[Hnki02.id],
                        routeID = it[Hnki02.routeID],
                        staCode = it[Hnki02.staCode],
                        fromStaCode = it[Hnki02.fromStaCode],
                        toStaCode = it[Hnki02.toStaCode],
                        staName = it[Hnki02.staName],
                    )
                }
        }
    }
}
