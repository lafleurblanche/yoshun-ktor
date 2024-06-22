package net.konohana.sakuya.yoshun.services.faredist.neue

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.faredist.neue.Neue06FareDistDataDto
import net.konohana.sakuya.yoshun.models.faredist.neue.Neue06FareDist
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Neue06FareDistService {
    private fun resultRowNeue06FareDist(row: ResultRow) = Neue06FareDistDataDto(
        id = row[Neue06FareDist.id].toString(),
        routeID = row[Neue06FareDist.routeID],
        staCode = row[Neue06FareDist.staCode],
        staName = row[Neue06FareDist.staName],
        strtPtStaCode = row[Neue06FareDist.strtPtStaCode],
        distance = row[Neue06FareDist.distance],
    )

    suspend fun getNeue06FareDist(): List<Neue06FareDistDataDto> {
        return KaedeDatabaseFactory.dbQuery {
            Neue06FareDist.selectAll().map(::resultRowNeue06FareDist)
        }
    }
}
