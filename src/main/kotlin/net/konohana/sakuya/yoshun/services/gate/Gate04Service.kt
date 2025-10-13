package net.konohana.sakuya.yoshun.services.gate

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.gate.Gate04Dto
import net.konohana.sakuya.yoshun.models.gate.Gate04
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Gate04Service {
    private fun resultRowGate04(row: ResultRow) = Gate04Dto(
        id = row[Gate04.id],
        routeID = row[Gate04.routeID],
        fromStaCode = row[Gate04.fromStaCode],
        toStaCode = row[Gate04.toStaCode],
        staCode = row[Gate04.staCode],
        staName = row[Gate04.staName],
    )

    suspend fun getGate04(): List<Gate04Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Gate04.selectAll().map(::resultRowGate04)
        }
    }

    suspend fun getGate04ByStaCode(staCode: String): Gate04Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Gate04.selectAll()
                .where { Gate04.staCode eq staCode }
                .singleOrNull()?.let {
                    Gate04Dto(
                        id = it[Gate04.id],
                        routeID = it[Gate04.routeID],
                        staCode = it[Gate04.staCode],
                        fromStaCode = it[Gate04.fromStaCode],
                        toStaCode = it[Gate04.toStaCode],
                        staName = it[Gate04.staName],
                    )
                }
        }
    }
}
