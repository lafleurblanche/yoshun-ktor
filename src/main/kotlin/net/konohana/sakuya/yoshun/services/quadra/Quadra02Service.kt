package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra02Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra02FrontendDto
import net.konohana.sakuya.yoshun.models.quadra.Quadra02
import net.konohana.sakuya.yoshun.models.routes.QuadraRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Quadra02Service {
    private fun resultRowQuadra02(row: ResultRow) = Quadra02Dto(
        id = row[Quadra02.id],
        routeID = row[Quadra02.routeID],
        fromStaCode = row[Quadra02.fromStaCode],
        toStaCode = row[Quadra02.toStaCode],
        staCode = row[Quadra02.staCode],
        staName = row[Quadra02.staName],
    )

    private fun resultRowQuadra02Frontend(row: ResultRow): Quadra02FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Quadra02.staName]
        val (staName1, staName2) = when {
            staName.length >= 4 -> Pair(staName.take(2), staName.substring(2, 4))
            else -> Pair(staName, "")
        }

        // 2. viaRouteName 取得とブランク化ロジック
        // LEFT JOINの結果、対応するレコードがない場合はnullになるため、
        // ?: "" (エルビス演算子) でnullの場合にブランクを設定します。
        val viaRouteName = row.getOrNull(QuadraRoutes.viaRouteName) ?: ""

        // 3. Frontend DTOの生成
        return Quadra02FrontendDto(
            id = row[Quadra02.id],
            viaRouteName = viaRouteName,
            staCode = row[Quadra02.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getQuadra02Frontend(): List<Quadra02FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra02
                // LEFT JOIN を使用して QuadraRoutes テーブルと結合
                .leftJoin(
                    QuadraRoutes,
                    { Quadra02.routeID },
                    { QuadraRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowQuadra02Frontend)
        }
    }

    suspend fun getQuadra02(): List<Quadra02Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra02.selectAll().map(::resultRowQuadra02)
        }
    }

    suspend fun getQuadra02ByStaCode(staCode: String): Quadra02Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra02.selectAll()
                .where { Quadra02.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra02Dto(
                        id = it[Quadra02.id],
                        routeID = it[Quadra02.routeID],
                        staCode = it[Quadra02.staCode],
                        fromStaCode = it[Quadra02.fromStaCode],
                        toStaCode = it[Quadra02.toStaCode],
                        staName = it[Quadra02.staName],
                    )
                }
        }
    }
}
