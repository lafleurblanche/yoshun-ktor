package net.konohana.sakuya.yoshun.services.faredist.neue

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.faredist.neue.Neue07FareDistDataDto
import net.konohana.sakuya.yoshun.models.faredist.neue.Neue07FareDist
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Neue07FareDistService {
    private fun resultRowNeue07FareDist(row: ResultRow) = Neue07FareDistDataDto(
        id = row[Neue07FareDist.id].toString(),
        routeID = row[Neue07FareDist.routeID],
        staCode = row[Neue07FareDist.staCode],
        staName = row[Neue07FareDist.staName],
        strtPtStaCode = row[Neue07FareDist.strtPtStaCode],
        distance = row[Neue07FareDist.distance],
    )

    suspend fun getNeue07FareDist(): List<Neue07FareDistDataDto> {
        return KaedeDatabaseFactory.dbQuery {
            Neue07FareDist.selectAll().map(::resultRowNeue07FareDist)
        }
    }
}
