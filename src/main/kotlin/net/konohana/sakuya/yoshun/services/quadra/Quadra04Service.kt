package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra04Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra04FrontendDto
import net.konohana.sakuya.yoshun.models.quadra.Quadra04
import net.konohana.sakuya.yoshun.models.routes.QuadraRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Quadra04Service {
    private fun resultRowQuadra04(row: ResultRow) = Quadra04Dto(
        id = row[Quadra04.id],
        routeID = row[Quadra04.routeID],
        fromStaCode = row[Quadra04.fromStaCode],
        toStaCode = row[Quadra04.toStaCode],
        staCode = row[Quadra04.staCode],
        staName = row[Quadra04.staName],
    )

    private fun resultRowQuadra04Frontend(row: ResultRow): Quadra04FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Quadra04.staName]
        val (staName1, staName2) = when {
            staName.length >= 4 -> Pair(staName.take(2), staName.substring(2, 4))
            else -> Pair(staName, "")
        }

        // 2. viaRouteName 取得とブランク化ロジック
        // LEFT JOINの結果、対応するレコードがない場合はnullになるため、
        // ?: "" (エルビス演算子) でnullの場合にブランクを設定します。
        val viaRouteName = row.getOrNull(QuadraRoutes.viaRouteName) ?: ""

        // 3. Frontend DTOの生成
        return Quadra04FrontendDto(
            id = row[Quadra04.id],
            viaRouteName = viaRouteName,
            staCode = row[Quadra04.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getQuadra04Frontend(): List<Quadra04FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra04
                // LEFT JOIN を使用して QuadraRoutes テーブルと結合
                .leftJoin(
                    QuadraRoutes,
                    { Quadra04.routeID },
                    { QuadraRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowQuadra04Frontend)
        }
    }

    suspend fun getQuadra04(): List<Quadra04Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra04.selectAll().map(::resultRowQuadra04)
        }
    }

    suspend fun getQuadra04ByStaCode(staCode: String): Quadra04Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra04.selectAll()
                .where { Quadra04.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra04Dto(
                        id = it[Quadra04.id],
                        routeID = it[Quadra04.routeID],
                        staCode = it[Quadra04.staCode],
                        fromStaCode = it[Quadra04.fromStaCode],
                        toStaCode = it[Quadra04.toStaCode],
                        staName = it[Quadra04.staName],
                    )
                }
        }
    }
}
