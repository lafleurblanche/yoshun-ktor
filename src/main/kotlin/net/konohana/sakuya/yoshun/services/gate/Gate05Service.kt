package net.konohana.sakuya.yoshun.services.gate

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.gate.Gate05Dto
import net.konohana.sakuya.yoshun.models.gate.Gate05
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Gate05Service {
    private fun resultRowGate05(row: ResultRow) = Gate05Dto(
        id = row[Gate05.id],
        routeID = row[Gate05.routeID],
        fromStaCode = row[Gate05.fromStaCode],
        toStaCode = row[Gate05.toStaCode],
        staCode = row[Gate05.staCode],
        staName = row[Gate05.staName],
    )

    suspend fun getGate05(): List<Gate05Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Gate05.selectAll().map(::resultRowGate05)
        }
    }

    suspend fun getGate05ByStaCode(staCode: String): Gate05Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Gate05.selectAll()
                .where { Gate05.staCode eq staCode }
                .singleOrNull()?.let {
                    Gate05Dto(
                        id = it[Gate05.id],
                        routeID = it[Gate05.routeID],
                        staCode = it[Gate05.staCode],
                        fromStaCode = it[Gate05.fromStaCode],
                        toStaCode = it[Gate05.toStaCode],
                        staName = it[Gate05.staName],
                    )
                }
        }
    }
}
