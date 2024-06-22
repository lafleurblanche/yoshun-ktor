package net.konohana.sakuya.yoshun.services.faredist.neue

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.faredist.neue.Neue02FareDistDataDto
import net.konohana.sakuya.yoshun.models.faredist.neue.Neue02FareDist
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Neue02FareDistService {
    private fun resultRowNeue02FareDist(row: ResultRow) = Neue02FareDistDataDto(
        id = row[Neue02FareDist.id].toString(),
        routeID = row[Neue02FareDist.routeID],
        staCode = row[Neue02FareDist.staCode],
        staName = row[Neue02FareDist.staName],
        strtPtStaCode = row[Neue02FareDist.strtPtStaCode],
        distance = row[Neue02FareDist.distance],
    )

    suspend fun getNeue02FareDist(): List<Neue02FareDistDataDto> {
        return KaedeDatabaseFactory.dbQuery {
            Neue02FareDist.selectAll().map(::resultRowNeue02FareDist)
        }
    }
}
