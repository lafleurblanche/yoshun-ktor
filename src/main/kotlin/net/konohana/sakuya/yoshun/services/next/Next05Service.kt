package net.konohana.sakuya.yoshun.services.next

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.next.Next05Dto
import net.konohana.sakuya.yoshun.models.next.Next05
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Next05Service {
    private fun resultRowNext05(row: ResultRow) = Next05Dto(
        id = row[Next05.id],
        routeID = row[Next05.routeID],
        fromStaCode = row[Next05.fromStaCode],
        toStaCode = row[Next05.toStaCode],
        staCode = row[Next05.staCode],
        staName = row[Next05.staName],
    )

    suspend fun getNext05(): List<Next05Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Next05.selectAll().map(::resultRowNext05)
        }
    }

    suspend fun getNext05ByStaCode(staCode: String): Next05Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Next05.selectAll()
                .where { Next05.staCode eq staCode }
                .singleOrNull()?.let {
                    Next05Dto(
                        id = it[Next05.id],
                        routeID = it[Next05.routeID],
                        staCode = it[Next05.staCode],
                        fromStaCode = it[Next05.fromStaCode],
                        toStaCode = it[Next05.toStaCode],
                        staName = it[Next05.staName],
                    )
                }
        }
    }
}
