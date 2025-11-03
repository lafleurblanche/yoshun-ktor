package net.konohana.sakuya.yoshun.services.next

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.next.Next01Dto
import net.konohana.sakuya.yoshun.models.next.Next01
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Next01Service {
    private fun resultRowNext01(row: ResultRow) = Next01Dto(
        id = row[Next01.id],
        routeID = row[Next01.routeID],
        fromStaCode = row[Next01.fromStaCode],
        toStaCode = row[Next01.toStaCode],
        staCode = row[Next01.staCode],
        staName = row[Next01.staName],
    )

    suspend fun getNext01(): List<Next01Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Next01.selectAll().map(::resultRowNext01)
        }
    }

    suspend fun getNext01ByStaCode(staCode: String): Next01Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Next01.selectAll()
                .where { Next01.staCode eq staCode }
                .singleOrNull()?.let {
                    Next01Dto(
                        id = it[Next01.id],
                        routeID = it[Next01.routeID],
                        staCode = it[Next01.staCode],
                        fromStaCode = it[Next01.fromStaCode],
                        toStaCode = it[Next01.toStaCode],
                        staName = it[Next01.staName],
                    )
                }
        }
    }
}
