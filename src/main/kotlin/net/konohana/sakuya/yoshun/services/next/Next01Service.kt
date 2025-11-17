package net.konohana.sakuya.yoshun.services.next

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.next.Next01Dto
import net.konohana.sakuya.yoshun.dtos.next.Next01FrontendDto
import net.konohana.sakuya.yoshun.models.next.Next01
import net.konohana.sakuya.yoshun.models.routes.NextRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Next01Service {
    private fun resultRowNext01(row: ResultRow) = Next01Dto(
        id = row[Next01.id],
        routeID = row[Next01.routeID],
        fromStaCode = row[Next01.fromStaCode],
        toStaCode = row[Next01.toStaCode],
        staCode = row[Next01.staCode],
        staName = row[Next01.staName],
    )

    private fun resultRowNext01Frontend(row: ResultRow): Next01FrontendDto {

        val staName = row[Next01.staName]
        val (staName1, staName2) = when {
            staName.length >= MIN_LENGTH_FOR_SPLIT -> Pair(
                staName.take(SPLIT_LENGTH),
                staName.drop(SPLIT_LENGTH).take(SPLIT_LENGTH)
            )
            else -> Pair(staName, "")
        }
        val viaRouteName = row.getOrNull(NextRoutes.viaRouteName) ?: ""

        return Next01FrontendDto(
            id = row[Next01.id],
            viaRouteName = viaRouteName,
            staCode = row[Next01.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getNext01Frontend(): List<Next01FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Next01

                .leftJoin(
                    NextRoutes,
                    { Next01.routeID },
                    { NextRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowNext01Frontend)
        }
    }

    suspend fun getNext01(): List<Next01Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Next01.selectAll().map(::resultRowNext01)
        }
    }

    suspend fun getNext01ByStaCode(staCode: String): Next01Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Next01.selectAll()
                .where { Next01.staCode eq staCode }
                .singleOrNull()?.let {
                    Next01Dto(
                        id = it[Next01.id],
                        routeID = it[Next01.routeID],
                        staCode = it[Next01.staCode],
                        fromStaCode = it[Next01.fromStaCode],
                        toStaCode = it[Next01.toStaCode],
                        staName = it[Next01.staName],
                    )
                }
        }
    }
}
