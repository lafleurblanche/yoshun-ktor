package net.konohana.sakuya.yoshun.services.faredist.neue

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.faredist.neue.Neue03FareDistDataDto
import net.konohana.sakuya.yoshun.models.faredist.neue.Neue03FareDist
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Neue03FareDistService {
    private fun resultRowNeue03FareDist(row: ResultRow) = Neue03FareDistDataDto(
        id = row[Neue03FareDist.id].toString(),
        routeID = row[Neue03FareDist.routeID],
        staCode = row[Neue03FareDist.staCode],
        staName = row[Neue03FareDist.staName],
        strtPtStaCode = row[Neue03FareDist.strtPtStaCode],
        distance = row[Neue03FareDist.distance],
    )

    suspend fun getNeue03FareDist(): List<Neue03FareDistDataDto> {
        return KaedeDatabaseFactory.dbQuery {
            Neue03FareDist.selectAll().map(::resultRowNeue03FareDist)
        }
    }
}
