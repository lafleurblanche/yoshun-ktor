package net.konohana.sakuya.yoshun.services.faredist.neue

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.faredist.neue.Neue05FareDistDataDto
import net.konohana.sakuya.yoshun.models.faredist.neue.Neue05FareDist
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Neue05FareDistService {
    private fun resultRowNeue05FareDist(row: ResultRow) = Neue05FareDistDataDto(
        id = row[Neue05FareDist.id].toString(),
        routeID = row[Neue05FareDist.routeID],
        staCode = row[Neue05FareDist.staCode],
        staName = row[Neue05FareDist.staName],
        strtPtStaCode = row[Neue05FareDist.strtPtStaCode],
        distance = row[Neue05FareDist.distance],
    )

    suspend fun getNeue05FareDist(): List<Neue05FareDistDataDto> {
        return KaedeDatabaseFactory.dbQuery {
            Neue05FareDist.selectAll().map(::resultRowNeue05FareDist)
        }
    }
}
