package net.konohana.sakuya.yoshun.services.next

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.next.Next04Dto
import net.konohana.sakuya.yoshun.dtos.next.Next04FrontendDto
import net.konohana.sakuya.yoshun.models.next.Next04
import net.konohana.sakuya.yoshun.models.routes.NextRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Next04Service {
    private fun resultRowNext04(row: ResultRow) = Next04Dto(
        id = row[Next04.id],
        routeID = row[Next04.routeID],
        fromStaCode = row[Next04.fromStaCode],
        toStaCode = row[Next04.toStaCode],
        staCode = row[Next04.staCode],
        staName = row[Next04.staName],
    )

    private fun resultRowNext04Frontend(row: ResultRow): Next04FrontendDto {

        val staName = row[Next04.staName]
        val (staName1, staName2) = when {
            staName.length >= MIN_LENGTH_FOR_SPLIT -> Pair(
                staName.take(SPLIT_LENGTH),
                staName.drop(SPLIT_LENGTH).take(SPLIT_LENGTH)
            )
            else -> Pair(staName, "")
        }
        val viaRouteName = row.getOrNull(NextRoutes.viaRouteName) ?: ""

        return Next04FrontendDto(
            id = row[Next04.id],
            viaRouteName = viaRouteName,
            staCode = row[Next04.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getNext04Frontend(): List<Next04FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Next04

                .leftJoin(
                    NextRoutes,
                    { Next04.routeID },
                    { NextRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowNext04Frontend)
        }
    }

    suspend fun getNext04(): List<Next04Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Next04.selectAll().map(::resultRowNext04)
        }
    }

    suspend fun getNext04ByStaCode(staCode: String): Next04Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Next04.selectAll()
                .where { Next04.staCode eq staCode }
                .singleOrNull()?.let {
                    Next04Dto(
                        id = it[Next04.id],
                        routeID = it[Next04.routeID],
                        staCode = it[Next04.staCode],
                        fromStaCode = it[Next04.fromStaCode],
                        toStaCode = it[Next04.toStaCode],
                        staName = it[Next04.staName],
                    )
                }
        }
    }
}
