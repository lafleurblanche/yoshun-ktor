package net.konohana.sakuya.yoshun.services.next

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.next.Next03Dto
import net.konohana.sakuya.yoshun.models.next.Next03
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Next03Service {
    private fun resultRowNext03(row: ResultRow) = Next03Dto(
        id = row[Next03.id],
        routeID = row[Next03.routeID],
        fromStaCode = row[Next03.fromStaCode],
        toStaCode = row[Next03.toStaCode],
        staCode = row[Next03.staCode],
        staName = row[Next03.staName],
    )

    suspend fun getNext03(): List<Next03Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Next03.selectAll().map(::resultRowNext03)
        }
    }

    suspend fun getNext03ByStaCode(staCode: String): Next03Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Next03.selectAll()
                .where { Next03.staCode eq staCode }
                .singleOrNull()?.let {
                    Next03Dto(
                        id = it[Next03.id],
                        routeID = it[Next03.routeID],
                        staCode = it[Next03.staCode],
                        fromStaCode = it[Next03.fromStaCode],
                        toStaCode = it[Next03.toStaCode],
                        staName = it[Next03.staName],
                    )
                }
        }
    }
}
