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
        distance = row[Argw01FareDist.distance],
    )

    suspend fun getArgw01FareDist(): List<Argw01FareDistDataDto> {
        return KaedeDatabaseFactory.dbQuery {
            Argw01FareDist.selectAll().map(::resultRowArgw01FareDist)
        }
    }

    suspend fun getArgw01FareDistByStaCode(staCode: String): Argw01FareDistDataDto? {
        return KaedeDatabaseFactory.dbQuery {
            Argw01FareDist.select(
                Argw01FareDist.id,
                Argw01FareDist.routeID,
                Argw01FareDist.staCode,
                Argw01FareDist.staName,
                Argw01FareDist.distance,
            ).where { Argw01FareDist.staCode eq staCode }.singleOrNull()?.let {
                Argw01FareDistDataDto(
                    id = it[Argw01FareDist.id].toString(),
                    routeID = it[Argw01FareDist.routeID],
                    staCode = it[Argw01FareDist.staCode],
                    staName = it[Argw01FareDist.staName],
                    distance = it[Argw01FareDist.distance],
                )
            }
        }
    }
}
