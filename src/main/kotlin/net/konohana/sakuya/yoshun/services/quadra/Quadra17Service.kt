package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra17Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra17FrontendDto
import net.konohana.sakuya.yoshun.models.quadra.Quadra17
import net.konohana.sakuya.yoshun.models.routes.QuadraRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Quadra17Service {
    private fun resultRowQuadra17(row: ResultRow) = Quadra17Dto(
        id = row[Quadra17.id],
        routeID = row[Quadra17.routeID],
        fromStaCode = row[Quadra17.fromStaCode],
        toStaCode = row[Quadra17.toStaCode],
        staCode = row[Quadra17.staCode],
        staName = row[Quadra17.staName],
    )

    private fun resultRowQuadra17Frontend(row: ResultRow): Quadra17FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Quadra17.staName]
        val (staName1, staName2) = when {
            staName.length >= MIN_LENGTH_FOR_SPLIT -> Pair(
                // 先頭から2文字を取得
                staName.take(SPLIT_LENGTH),
                // 2文字目以降を取得し、そこから2文字を取得 (4文字目まで)
                staName.drop(SPLIT_LENGTH).take(SPLIT_LENGTH)
            )
            else -> Pair(staName, "")
        }

        // 2. viaRouteName 取得とブランク化ロジック
        // LEFT JOINの結果、対応するレコードがない場合はnullになるため、
        // ?: "" (エルビス演算子) でnullの場合にブランクを設定します。
        val viaRouteName = row.getOrNull(QuadraRoutes.viaRouteName) ?: ""

        // 3. Frontend DTOの生成
        return Quadra17FrontendDto(
            id = row[Quadra17.id],
            viaRouteName = viaRouteName,
            staCode = row[Quadra17.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getQuadra17Frontend(): List<Quadra17FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra17
                // LEFT JOIN を使用して QuadraRoutes テーブルと結合
                .leftJoin(
                    QuadraRoutes,
                    { Quadra17.routeID },
                    { QuadraRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowQuadra17Frontend)
        }
    }

    suspend fun getQuadra17(): List<Quadra17Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra17.selectAll().map(::resultRowQuadra17)
        }
    }

    suspend fun getQuadra17ByStaCode(staCode: String): Quadra17Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra17.selectAll()
                .where { Quadra17.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra17Dto(
                        id = it[Quadra17.id],
                        routeID = it[Quadra17.routeID],
                        staCode = it[Quadra17.staCode],
                        fromStaCode = it[Quadra17.fromStaCode],
                        toStaCode = it[Quadra17.toStaCode],
                        staName = it[Quadra17.staName],
                    )
                }
        }
    }
}
