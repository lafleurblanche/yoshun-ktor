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
        distance = row[Hrgi02FareDist.distance],
    )

    suspend fun getHrgi02FareDist(): List<Hrgi02FareDistDataDto> {
        return KaedeDatabaseFactory.dbQuery {
            Hrgi02FareDist.selectAll().map(::resultRowHrgi02FareDist)
        }
    }

    suspend fun getHrgi02FareDistByStaCode(staCode: String): Hrgi02FareDistDataDto? {
        return KaedeDatabaseFactory.dbQuery {
            Hrgi02FareDist.select(
                Hrgi02FareDist.id,
                Hrgi02FareDist.routeID,
                Hrgi02FareDist.staCode,
                Hrgi02FareDist.staName,
                Hrgi02FareDist.distance,
            ).where { Hrgi02FareDist.staCode eq staCode }.singleOrNull()?.let {
                Hrgi02FareDistDataDto(
                    id = it[Hrgi02FareDist.id].toString(),
                    routeID = it[Hrgi02FareDist.routeID],
                    staCode = it[Hrgi02FareDist.staCode],
                    staName = it[Hrgi02FareDist.staName],
                    distance = it[Hrgi02FareDist.distance],
                )
            }
        }
    }
}
