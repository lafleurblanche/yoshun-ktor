package net.konohana.sakuya.yoshun.services.gate

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.gate.Gate03Dto
import net.konohana.sakuya.yoshun.models.gate.Gate03
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Gate03Service {
    private fun resultRowGate03(row: ResultRow) = Gate03Dto(
        id = row[Gate03.id],
        routeID = row[Gate03.routeID],
        fromStaCode = row[Gate03.fromStaCode],
        toStaCode = row[Gate03.toStaCode],
        staCode = row[Gate03.staCode],
        staName = row[Gate03.staName],
    )

    suspend fun getGate03(): List<Gate03Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Gate03.selectAll().map(::resultRowGate03)
        }
    }

    suspend fun getGate03ByStaCode(staCode: String): Gate03Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Gate03.selectAll()
                .where { Gate03.staCode eq staCode }
                .singleOrNull()?.let {
                    Gate03Dto(
                        id = it[Gate03.id],
                        routeID = it[Gate03.routeID],
                        staCode = it[Gate03.staCode],
                        fromStaCode = it[Gate03.fromStaCode],
                        toStaCode = it[Gate03.toStaCode],
                        staName = it[Gate03.staName],
                    )
                }
        }
    }
}
