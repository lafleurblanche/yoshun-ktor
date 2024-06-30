package net.konohana.sakuya.yoshun.services.faredist.hrgi

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.faredist.hrgi.Hrgi02FareDistDataDto
import net.konohana.sakuya.yoshun.models.faredist.hrgi.Hrgi02FareDist
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Hrgi02FareDistService {
    private fun resultRowHrgi02FareDist(row: ResultRow) = Hrgi02FareDistDataDto(
        id = row[Hrgi02FareDist.id].toString(),
        routeID = row[Hrgi02FareDist.routeID],
        staCode = row[Hrgi02FareDist.staCode],
        staName = row[Hrgi02FareDist.staName],
        strtPtStaCode = row[Hrgi02FareDist.strtPtStaCode],
        distance = row[Hrgi02FareDist.distance],
    )

    suspend fun getHrgi02FareDist(): List<Hrgi02FareDistDataDto> {
        return KaedeDatabaseFactory.dbQuery {
            Hrgi02FareDist.selectAll().map(::resultRowHrgi02FareDist)
        }
    }
}
