package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra24Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra24FrontendDto
import net.konohana.sakuya.yoshun.models.quadra.Quadra24
import net.konohana.sakuya.yoshun.models.routes.QuadraRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Quadra24Service {
    private fun resultRowQuadra24(row: ResultRow) = Quadra24Dto(
        id = row[Quadra24.id],
        routeID = row[Quadra24.routeID],
        fromStaCode = row[Quadra24.fromStaCode],
        toStaCode = row[Quadra24.toStaCode],
        staCode = row[Quadra24.staCode],
        staName = row[Quadra24.staName],
    )

    private fun resultRowQuadra24Frontend(row: ResultRow): Quadra24FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Quadra24.staName]
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
        return Quadra24FrontendDto(
            id = row[Quadra24.id],
            viaRouteName = viaRouteName,
            staCode = row[Quadra24.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getQuadra24Frontend(): List<Quadra24FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra24
                // LEFT JOIN を使用して QuadraRoutes テーブルと結合
                .leftJoin(
                    QuadraRoutes,
                    { Quadra24.routeID },
                    { QuadraRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowQuadra24Frontend)
        }
    }

    suspend fun getQuadra24(): List<Quadra24Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra24.selectAll().map(::resultRowQuadra24)
        }
    }

    suspend fun getQuadra24ByStaCode(staCode: String): Quadra24Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra24.selectAll()
                .where { Quadra24.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra24Dto(
                        id = it[Quadra24.id],
                        routeID = it[Quadra24.routeID],
                        staCode = it[Quadra24.staCode],
                        fromStaCode = it[Quadra24.fromStaCode],
                        toStaCode = it[Quadra24.toStaCode],
                        staName = it[Quadra24.staName],
                    )
                }
        }
    }
}
