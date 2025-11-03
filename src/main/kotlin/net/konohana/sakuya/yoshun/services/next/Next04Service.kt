package net.konohana.sakuya.yoshun.services.next

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.next.Next04Dto
import net.konohana.sakuya.yoshun.models.next.Next04
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Next04Service {
    private fun resultRowNext04(row: ResultRow) = Next04Dto(
        id = row[Next04.id],
        routeID = row[Next04.routeID],
        fromStaCode = row[Next04.fromStaCode],
        toStaCode = row[Next04.toStaCode],
        staCode = row[Next04.staCode],
        staName = row[Next04.staName],
    )

    suspend fun getNext04(): List<Next04Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Next04.selectAll().map(::resultRowNext04)
        }
    }

    suspend fun getNext04ByStaCode(staCode: String): Next04Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Next04.selectAll()
                .where { Next04.staCode eq staCode }
                .singleOrNull()?.let {
                    Next04Dto(
                        id = it[Next04.id],
                        routeID = it[Next04.routeID],
                        staCode = it[Next04.staCode],
                        fromStaCode = it[Next04.fromStaCode],
                        toStaCode = it[Next04.toStaCode],
                        staName = it[Next04.staName],
                    )
                }
        }
    }
}
