package net.konohana.sakuya.yoshun.services.enju

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.enju.Enju02Dto
import net.konohana.sakuya.yoshun.models.enju.Enju02
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Enju02Service {
    private fun resultRowEnju02(row: ResultRow) = Enju02Dto(
        id = row[Enju02.id],
        routeID = row[Enju02.routeID],
        fromStaCode = row[Enju02.fromStaCode],
        toStaCode = row[Enju02.toStaCode],
        staCode = row[Enju02.staCode],
        staName = row[Enju02.staName],
    )

    suspend fun getEnju02(): List<Enju02Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Enju02.selectAll().map(::resultRowEnju02)
        }
    }

    suspend fun getEnju02ByStaCode(staCode: String): Enju02Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Enju02.selectAll()
                .where { Enju02.staCode eq staCode }
                .singleOrNull()?.let {
                    Enju02Dto(
                        id = it[Enju02.id],
                        routeID = it[Enju02.routeID],
                        staCode = it[Enju02.staCode],
                        fromStaCode = it[Enju02.fromStaCode],
                        toStaCode = it[Enju02.toStaCode],
                        staName = it[Enju02.staName],
                    )
                }
        }
    }
}
