package net.konohana.sakuya.yoshun.services.faredist.neue

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.faredist.neue.Neue08FareDistDataDto
import net.konohana.sakuya.yoshun.models.faredist.neue.Neue08FareDist
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Neue08FareDistService {
    private fun resultRowNeue08FareDist(row: ResultRow) = Neue08FareDistDataDto(
        id = row[Neue08FareDist.id].toString(),
        routeID = row[Neue08FareDist.routeID],
        staCode = row[Neue08FareDist.staCode],
        staName = row[Neue08FareDist.staName],
        strtPtStaCode = row[Neue08FareDist.strtPtStaCode],
        distance = row[Neue08FareDist.distance],
    )

    suspend fun getNeue08FareDist(): List<Neue08FareDistDataDto> {
        return KaedeDatabaseFactory.dbQuery {
            Neue08FareDist.selectAll().map(::resultRowNeue08FareDist)
        }
    }
}
