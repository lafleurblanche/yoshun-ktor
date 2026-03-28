package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra01Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra01FrontendDto
import net.konohana.sakuya.yoshun.models.quadra.Quadra01
import net.konohana.sakuya.yoshun.models.routes.QuadraRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Quadra01Service {
    private fun resultRowQuadra01(row: ResultRow) = Quadra01Dto(
        id = row[Quadra01.id],
        routeID = row[Quadra01.routeID],
        fromStaCode = row[Quadra01.fromStaCode],
        toStaCode = row[Quadra01.toStaCode],
        staCode = row[Quadra01.staCode],
        staName = row[Quadra01.staName],
    )

    private fun resultRowQuadra01Frontend(row: ResultRow): Quadra01FrontendDto {

        val staName = row[Quadra01.staName]
        val (staName1, staName2) = when {
            staName.length >= MIN_LENGTH_FOR_SPLIT -> Pair(
                staName.take(SPLIT_LENGTH),
                staName.drop(SPLIT_LENGTH).take(SPLIT_LENGTH)
            )
            else -> Pair(staName, "")
        }

        val viaRouteName = row.getOrNull(QuadraRoutes.viaRouteName) ?: ""

        return Quadra01FrontendDto(
            id = row[Quadra01.id],
            viaRouteName = viaRouteName,
            staCode = row[Quadra01.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getQuadra01Frontend(): List<Quadra01FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra01
                .leftJoin(
                    QuadraRoutes,
                    { Quadra01.routeID },
                    { QuadraRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowQuadra01Frontend)
        }
    }

    suspend fun getQuadra01(): List<Quadra01Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra01.selectAll().map(::resultRowQuadra01)
        }
    }

    suspend fun getQuadra01ByStaCode(staCode: String): Quadra01Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra01.selectAll()
                .where { Quadra01.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra01Dto(
                        id = it[Quadra01.id],
                        routeID = it[Quadra01.routeID],
                        staCode = it[Quadra01.staCode],
                        fromStaCode = it[Quadra01.fromStaCode],
                        toStaCode = it[Quadra01.toStaCode],
                        staName = it[Quadra01.staName],
                    )
                }
        }
    }
}
