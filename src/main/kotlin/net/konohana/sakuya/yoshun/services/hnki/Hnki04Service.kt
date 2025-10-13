package net.konohana.sakuya.yoshun.services.hnki

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.hnki.Hnki04Dto
import net.konohana.sakuya.yoshun.setup.models.hnki.Hnki04
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Hnki04Service {
    private fun resultRowHnki04(row: ResultRow) = Hnki04Dto(
        id = row[Hnki04.id],
        routeID = row[Hnki04.routeID],
        fromStaCode = row[Hnki04.fromStaCode],
        toStaCode = row[Hnki04.toStaCode],
        staCode = row[Hnki04.staCode],
        staName = row[Hnki04.staName],
    )

    suspend fun getHnki04(): List<Hnki04Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Hnki04.selectAll().map(::resultRowHnki04)
        }
    }

    suspend fun getHnki04ByStaCode(staCode: String): Hnki04Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Hnki04.selectAll()
                .where { Hnki04.staCode eq staCode }
                .singleOrNull()?.let {
                    Hnki04Dto(
                        id = it[Hnki04.id],
                        routeID = it[Hnki04.routeID],
                        staCode = it[Hnki04.staCode],
                        fromStaCode = it[Hnki04.fromStaCode],
                        toStaCode = it[Hnki04.toStaCode],
                        staName = it[Hnki04.staName],
                    )
                }
        }
    }
}
