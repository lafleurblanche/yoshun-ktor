package net.konohana.sakuya.yoshun.services.faredist.neue

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.faredist.neue.Neue04FareDistDataDto
import net.konohana.sakuya.yoshun.models.faredist.neue.Neue04FareDist
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Neue04FareDistService {
    private fun resultRowNeue04FareDist(row: ResultRow) = Neue04FareDistDataDto(
        id = row[Neue04FareDist.id].toString(),
        routeID = row[Neue04FareDist.routeID],
        staCode = row[Neue04FareDist.staCode],
        staName = row[Neue04FareDist.staName],
        strtPtStaCode = row[Neue04FareDist.strtPtStaCode],
        distance = row[Neue04FareDist.distance],
    )

    suspend fun getNeue04FareDist(): List<Neue04FareDistDataDto> {
        return KaedeDatabaseFactory.dbQuery {
            Neue04FareDist.selectAll().map(::resultRowNeue04FareDist)
        }
    }
}
