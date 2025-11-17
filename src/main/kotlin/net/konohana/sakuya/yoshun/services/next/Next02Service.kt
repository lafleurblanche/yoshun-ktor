package net.konohana.sakuya.yoshun.services.next

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.next.Next02Dto
import net.konohana.sakuya.yoshun.dtos.next.Next02FrontendDto
import net.konohana.sakuya.yoshun.models.next.Next02
import net.konohana.sakuya.yoshun.models.routes.NextRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Next02Service {
    private fun resultRowNext02(row: ResultRow) = Next02Dto(
        id = row[Next02.id],
        routeID = row[Next02.routeID],
        fromStaCode = row[Next02.fromStaCode],
        toStaCode = row[Next02.toStaCode],
        staCode = row[Next02.staCode],
        staName = row[Next02.staName],
    )

    private fun resultRowNext02Frontend(row: ResultRow): Next02FrontendDto {

        val staName = row[Next02.staName]
        val (staName1, staName2) = when {
            staName.length >= MIN_LENGTH_FOR_SPLIT -> Pair(
                staName.take(SPLIT_LENGTH),
                staName.drop(SPLIT_LENGTH).take(SPLIT_LENGTH)
            )
            else -> Pair(staName, "")
        }
        val viaRouteName = row.getOrNull(NextRoutes.viaRouteName) ?: ""

        return Next02FrontendDto(
            id = row[Next02.id],
            viaRouteName = viaRouteName,
            staCode = row[Next02.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getNext02Frontend(): List<Next02FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Next02

                .leftJoin(
                    NextRoutes,
                    { Next02.routeID },
                    { NextRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowNext02Frontend)
        }
    }

    suspend fun getNext02(): List<Next02Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Next02.selectAll().map(::resultRowNext02)
        }
    }

    suspend fun getNext02ByStaCode(staCode: String): Next02Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Next02.selectAll()
                .where { Next02.staCode eq staCode }
                .singleOrNull()?.let {
                    Next02Dto(
                        id = it[Next02.id],
                        routeID = it[Next02.routeID],
                        staCode = it[Next02.staCode],
                        fromStaCode = it[Next02.fromStaCode],
                        toStaCode = it[Next02.toStaCode],
                        staName = it[Next02.staName],
                    )
                }
        }
    }
}
