package net.konohana.sakuya.yoshun.services.faredist.hrgi

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.faredist.hrgi.Hrgi01FareDistDataDto
import net.konohana.sakuya.yoshun.models.faredist.hrgi.Hrgi01FareDist
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Hrgi01FareDistService {
    private fun resultRowHrgi01FareDist(row: ResultRow) = Hrgi01FareDistDataDto(
        id = row[Hrgi01FareDist.id].toString(),
        routeID = row[Hrgi01FareDist.routeID],
        staCode = row[Hrgi01FareDist.staCode],
        staName = row[Hrgi01FareDist.staName],
        strtPtStaCode = row[Hrgi01FareDist.strtPtStaCode],
        distance = row[Hrgi01FareDist.distance],
    )

    suspend fun getHrgi01FareDist(): List<Hrgi01FareDistDataDto> {
        return KaedeDatabaseFactory.dbQuery {
            Hrgi01FareDist.selectAll().map(::resultRowHrgi01FareDist)
        }
    }
}
