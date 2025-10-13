package net.konohana.sakuya.yoshun.services.gate

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.gate.Gate02Dto
import net.konohana.sakuya.yoshun.models.gate.Gate02
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Gate02Service {
    private fun resultRowGate02(row: ResultRow) = Gate02Dto(
        id = row[Gate02.id],
        routeID = row[Gate02.routeID],
        fromStaCode = row[Gate02.fromStaCode],
        toStaCode = row[Gate02.toStaCode],
        staCode = row[Gate02.staCode],
        staName = row[Gate02.staName],
    )

    suspend fun getGate02(): List<Gate02Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Gate02.selectAll().map(::resultRowGate02)
        }
    }

    suspend fun getGate02ByStaCode(staCode: String): Gate02Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Gate02.selectAll()
                .where { Gate02.staCode eq staCode }
                .singleOrNull()?.let {
                    Gate02Dto(
                        id = it[Gate02.id],
                        routeID = it[Gate02.routeID],
                        staCode = it[Gate02.staCode],
                        fromStaCode = it[Gate02.fromStaCode],
                        toStaCode = it[Gate02.toStaCode],
                        staName = it[Gate02.staName],
                    )
                }
        }
    }
}
