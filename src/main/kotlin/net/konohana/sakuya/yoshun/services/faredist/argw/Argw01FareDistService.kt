package net.konohana.sakuya.yoshun.services.faredist.argw

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.faredist.argw.Argw01FareDistDataDto
import net.konohana.sakuya.yoshun.models.faredist.argw.Argw01FareDist
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Argw01FareDistService {
    private fun resultRowArgw01FareDist(row: ResultRow) = Argw01FareDistDataDto(
        id = row[Argw01FareDist.id].toString(),
        routeID = row[Argw01FareDist.routeID],
        staCode = row[Argw01FareDist.staCode],
        staName = row[Argw01FareDist.staName],
        strtPtStaCode = row[Argw01FareDist.strtPtStaCode],
        distance = row[Argw01FareDist.distance],
    )

    suspend fun getArgw01FareDist(): List<Argw01FareDistDataDto> {
        return KaedeDatabaseFactory.dbQuery {
            Argw01FareDist.selectAll().map(::resultRowArgw01FareDist)
        }
    }
}
