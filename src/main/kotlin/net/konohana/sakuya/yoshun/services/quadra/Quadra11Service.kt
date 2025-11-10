package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra11Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra11FrontendDto
import net.konohana.sakuya.yoshun.models.quadra.Quadra11
import net.konohana.sakuya.yoshun.models.routes.QuadraRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Quadra11Service {
    private fun resultRowQuadra11(row: ResultRow) = Quadra11Dto(
        id = row[Quadra11.id],
        routeID = row[Quadra11.routeID],
        fromStaCode = row[Quadra11.fromStaCode],
        toStaCode = row[Quadra11.toStaCode],
        staCode = row[Quadra11.staCode],
        staName = row[Quadra11.staName],
    )

    private fun resultRowQuadra11Frontend(row: ResultRow): Quadra11FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Quadra11.staName]
        val (staName1, staName2) = when {
            staName.length >= 4 -> Pair(staName.take(2), staName.substring(2, 4))
            else -> Pair(staName, "")
        }

        // 2. viaRouteName 取得とブランク化ロジック
        // LEFT JOINの結果、対応するレコードがない場合はnullになるため、
        // ?: "" (エルビス演算子) でnullの場合にブランクを設定します。
        val viaRouteName = row.getOrNull(QuadraRoutes.viaRouteName) ?: ""

        // 3. Frontend DTOの生成
        return Quadra11FrontendDto(
            id = row[Quadra11.id],
            viaRouteName = viaRouteName,
            staCode = row[Quadra11.staCode],
            staName1 = staName1,
            staName2 = staName2,
            staName = staName,
        )
    }

    suspend fun getQuadra11Frontend(): List<Quadra11FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra11
                // LEFT JOIN を使用して QuadraRoutes テーブルと結合
                .leftJoin(
                    QuadraRoutes,
                    { Quadra11.routeID },
                    { QuadraRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowQuadra11Frontend)
        }
    }

    suspend fun getQuadra11(): List<Quadra11Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra11.selectAll().map(::resultRowQuadra11)
        }
    }

    suspend fun getQuadra11ByStaCode(staCode: String): Quadra11Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra11.selectAll()
                .where { Quadra11.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra11Dto(
                        id = it[Quadra11.id],
                        routeID = it[Quadra11.routeID],
                        staCode = it[Quadra11.staCode],
                        fromStaCode = it[Quadra11.fromStaCode],
                        toStaCode = it[Quadra11.toStaCode],
                        staName = it[Quadra11.staName],
                    )
                }
        }
    }
}
