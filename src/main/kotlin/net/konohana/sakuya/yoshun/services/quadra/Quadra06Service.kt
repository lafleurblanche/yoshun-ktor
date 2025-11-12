package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra06Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra06FrontendDto
import net.konohana.sakuya.yoshun.models.quadra.Quadra06
import net.konohana.sakuya.yoshun.models.routes.QuadraRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Quadra06Service {
    private fun resultRowQuadra06(row: ResultRow) = Quadra06Dto(
        id = row[Quadra06.id],
        routeID = row[Quadra06.routeID],
        fromStaCode = row[Quadra06.fromStaCode],
        toStaCode = row[Quadra06.toStaCode],
        staCode = row[Quadra06.staCode],
        staName = row[Quadra06.staName],
    )

    private fun resultRowQuadra06Frontend(row: ResultRow): Quadra06FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Quadra06.staName]
        val (staName1, staName2) = when {
            staName.length >= 4 -> Pair(staName.take(2), staName.substring(2, 4))
            else -> Pair(staName, "")
        }

        // 2. viaRouteName 取得とブランク化ロジック
        // LEFT JOINの結果、対応するレコードがない場合はnullになるため、
        // ?: "" (エルビス演算子) でnullの場合にブランクを設定します。
        val viaRouteName = row.getOrNull(QuadraRoutes.viaRouteName) ?: ""

        // 3. Frontend DTOの生成
        return Quadra06FrontendDto(
            id = row[Quadra06.id],
            viaRouteName = viaRouteName,
            staCode = row[Quadra06.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getQuadra06Frontend(): List<Quadra06FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra06
                // LEFT JOIN を使用して QuadraRoutes テーブルと結合
                .leftJoin(
                    QuadraRoutes,
                    { Quadra06.routeID },
                    { QuadraRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowQuadra06Frontend)
        }
    }

    suspend fun getQuadra06(): List<Quadra06Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra06.selectAll().map(::resultRowQuadra06)
        }
    }

    suspend fun getQuadra06ByStaCode(staCode: String): Quadra06Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra06.selectAll()
                .where { Quadra06.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra06Dto(
                        id = it[Quadra06.id],
                        routeID = it[Quadra06.routeID],
                        staCode = it[Quadra06.staCode],
                        fromStaCode = it[Quadra06.fromStaCode],
                        toStaCode = it[Quadra06.toStaCode],
                        staName = it[Quadra06.staName],
                    )
                }
        }
    }
}
