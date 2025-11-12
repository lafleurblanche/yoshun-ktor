package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra07Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra07FrontendDto
import net.konohana.sakuya.yoshun.models.quadra.Quadra07
import net.konohana.sakuya.yoshun.models.routes.QuadraRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Quadra07Service {
    private fun resultRowQuadra07(row: ResultRow) = Quadra07Dto(
        id = row[Quadra07.id],
        routeID = row[Quadra07.routeID],
        fromStaCode = row[Quadra07.fromStaCode],
        toStaCode = row[Quadra07.toStaCode],
        staCode = row[Quadra07.staCode],
        staName = row[Quadra07.staName],
    )

    private fun resultRowQuadra07Frontend(row: ResultRow): Quadra07FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Quadra07.staName]
        val (staName1, staName2) = when {
            staName.length >= 4 -> Pair(staName.take(2), staName.substring(2, 4))
            else -> Pair(staName, "")
        }

        // 2. viaRouteName 取得とブランク化ロジック
        // LEFT JOINの結果、対応するレコードがない場合はnullになるため、
        // ?: "" (エルビス演算子) でnullの場合にブランクを設定します。
        val viaRouteName = row.getOrNull(QuadraRoutes.viaRouteName) ?: ""

        // 3. Frontend DTOの生成
        return Quadra07FrontendDto(
            id = row[Quadra07.id],
            viaRouteName = viaRouteName,
            staCode = row[Quadra07.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getQuadra07Frontend(): List<Quadra07FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra07
                // LEFT JOIN を使用して QuadraRoutes テーブルと結合
                .leftJoin(
                    QuadraRoutes,
                    { Quadra07.routeID },
                    { QuadraRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowQuadra07Frontend)
        }
    }

    suspend fun getQuadra07(): List<Quadra07Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra07.selectAll().map(::resultRowQuadra07)
        }
    }

    suspend fun getQuadra07ByStaCode(staCode: String): Quadra07Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra07.selectAll()
                .where { Quadra07.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra07Dto(
                        id = it[Quadra07.id],
                        routeID = it[Quadra07.routeID],
                        staCode = it[Quadra07.staCode],
                        fromStaCode = it[Quadra07.fromStaCode],
                        toStaCode = it[Quadra07.toStaCode],
                        staName = it[Quadra07.staName],
                    )
                }
        }
    }
}
