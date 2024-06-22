package net.konohana.sakuya.yoshun.services.faredist.neue

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.faredist.neue.Neue01FareDistDataDto
import net.konohana.sakuya.yoshun.models.faredist.neue.Neue01FareDist
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Neue01FareDistService {
    private fun resultRowNeue01FareDist(row: ResultRow) = Neue01FareDistDataDto(
        id = row[Neue01FareDist.id].toString(),
        routeID = row[Neue01FareDist.routeID],
        staCode = row[Neue01FareDist.staCode],
        staName = row[Neue01FareDist.staName],
        strtPtStaCode = row[Neue01FareDist.strtPtStaCode],
        distance = row[Neue01FareDist.distance],
    )

    suspend fun getNeue01FareDist(): List<Neue01FareDistDataDto> {
        return KaedeDatabaseFactory.dbQuery {
            Neue01FareDist.selectAll().map(::resultRowNeue01FareDist)
        }
    }
}
