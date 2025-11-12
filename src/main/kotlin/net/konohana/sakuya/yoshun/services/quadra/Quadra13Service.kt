package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra13Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra13FrontendDto
import net.konohana.sakuya.yoshun.models.quadra.Quadra13
import net.konohana.sakuya.yoshun.models.routes.QuadraRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Quadra13Service {
    private fun resultRowQuadra13(row: ResultRow) = Quadra13Dto(
        id = row[Quadra13.id],
        routeID = row[Quadra13.routeID],
        fromStaCode = row[Quadra13.fromStaCode],
        toStaCode = row[Quadra13.toStaCode],
        staCode = row[Quadra13.staCode],
        staName = row[Quadra13.staName],
    )

    private fun resultRowQuadra13Frontend(row: ResultRow): Quadra13FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Quadra13.staName]
        val (staName1, staName2) = when {
            staName.length >= 4 -> Pair(staName.take(2), staName.substring(2, 4))
            else -> Pair(staName, "")
        }

        // 2. viaRouteName 取得とブランク化ロジック
        // LEFT JOINの結果、対応するレコードがない場合はnullになるため、
        // ?: "" (エルビス演算子) でnullの場合にブランクを設定します。
        val viaRouteName = row.getOrNull(QuadraRoutes.viaRouteName) ?: ""

        // 3. Frontend DTOの生成
        return Quadra13FrontendDto(
            id = row[Quadra13.id],
            viaRouteName = viaRouteName,
            staCode = row[Quadra13.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getQuadra13Frontend(): List<Quadra13FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra13
                // LEFT JOIN を使用して QuadraRoutes テーブルと結合
                .leftJoin(
                    QuadraRoutes,
                    { Quadra13.routeID },
                    { QuadraRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowQuadra13Frontend)
        }
    }

    suspend fun getQuadra13(): List<Quadra13Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra13.selectAll().map(::resultRowQuadra13)
        }
    }

    suspend fun getQuadra13ByStaCode(staCode: String): Quadra13Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra13.selectAll()
                .where { Quadra13.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra13Dto(
                        id = it[Quadra13.id],
                        routeID = it[Quadra13.routeID],
                        staCode = it[Quadra13.staCode],
                        fromStaCode = it[Quadra13.fromStaCode],
                        toStaCode = it[Quadra13.toStaCode],
                        staName = it[Quadra13.staName],
                    )
                }
        }
    }
}
