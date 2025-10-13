package net.konohana.sakuya.yoshun.services.gate

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.gate.Gate01Dto
import net.konohana.sakuya.yoshun.models.gate.Gate01
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Gate01Service {
    private fun resultRowGate01(row: ResultRow) = Gate01Dto(
        id = row[Gate01.id],
        routeID = row[Gate01.routeID],
        fromStaCode = row[Gate01.fromStaCode],
        toStaCode = row[Gate01.toStaCode],
        staCode = row[Gate01.staCode],
        staName = row[Gate01.staName],
    )

    suspend fun getGate01(): List<Gate01Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Gate01.selectAll().map(::resultRowGate01)
        }
    }

    suspend fun getGate01ByStaCode(staCode: String): Gate01Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Gate01.selectAll()
                .where { Gate01.staCode eq staCode }
                .singleOrNull()?.let {
                    Gate01Dto(
                        id = it[Gate01.id],
                        routeID = it[Gate01.routeID],
                        staCode = it[Gate01.staCode],
                        fromStaCode = it[Gate01.fromStaCode],
                        toStaCode = it[Gate01.toStaCode],
                        staName = it[Gate01.staName],
                    )
                }
        }
    }
}
