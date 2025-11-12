package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra14Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra14FrontendDto
import net.konohana.sakuya.yoshun.models.quadra.Quadra14
import net.konohana.sakuya.yoshun.models.routes.QuadraRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Quadra14Service {
    private fun resultRowQuadra14(row: ResultRow) = Quadra14Dto(
        id = row[Quadra14.id],
        routeID = row[Quadra14.routeID],
        fromStaCode = row[Quadra14.fromStaCode],
        toStaCode = row[Quadra14.toStaCode],
        staCode = row[Quadra14.staCode],
        staName = row[Quadra14.staName],
    )

    private fun resultRowQuadra14Frontend(row: ResultRow): Quadra14FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Quadra14.staName]
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
        return Quadra14FrontendDto(
            id = row[Quadra14.id],
            viaRouteName = viaRouteName,
            staCode = row[Quadra14.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getQuadra14Frontend(): List<Quadra14FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra14
                // LEFT JOIN を使用して QuadraRoutes テーブルと結合
                .leftJoin(
                    QuadraRoutes,
                    { Quadra14.routeID },
                    { QuadraRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowQuadra14Frontend)
        }
    }

    suspend fun getQuadra14(): List<Quadra14Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra14.selectAll().map(::resultRowQuadra14)
        }
    }

    suspend fun getQuadra14ByStaCode(staCode: String): Quadra14Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra14.selectAll()
                .where { Quadra14.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra14Dto(
                        id = it[Quadra14.id],
                        routeID = it[Quadra14.routeID],
                        staCode = it[Quadra14.staCode],
                        fromStaCode = it[Quadra14.fromStaCode],
                        toStaCode = it[Quadra14.toStaCode],
                        staName = it[Quadra14.staName],
                    )
                }
        }
    }
}
