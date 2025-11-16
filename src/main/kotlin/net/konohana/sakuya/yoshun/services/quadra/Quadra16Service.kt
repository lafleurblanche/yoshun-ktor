package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra16Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra16FrontendDto
import net.konohana.sakuya.yoshun.models.quadra.Quadra16
import net.konohana.sakuya.yoshun.models.routes.QuadraRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Quadra16Service {
    private fun resultRowQuadra16(row: ResultRow) = Quadra16Dto(
        id = row[Quadra16.id],
        routeID = row[Quadra16.routeID],
        fromStaCode = row[Quadra16.fromStaCode],
        toStaCode = row[Quadra16.toStaCode],
        staCode = row[Quadra16.staCode],
        staName = row[Quadra16.staName],
    )

    private fun resultRowQuadra16Frontend(row: ResultRow): Quadra16FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Quadra16.staName]
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
        return Quadra16FrontendDto(
            id = row[Quadra16.id],
            viaRouteName = viaRouteName,
            staCode = row[Quadra16.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getQuadra16Frontend(): List<Quadra16FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra16
                // LEFT JOIN を使用して QuadraRoutes テーブルと結合
                .leftJoin(
                    QuadraRoutes,
                    { Quadra16.routeID },
                    { QuadraRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowQuadra16Frontend)
        }
    }

    suspend fun getQuadra16(): List<Quadra16Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra16.selectAll().map(::resultRowQuadra16)
        }
    }

    suspend fun getQuadra16ByStaCode(staCode: String): Quadra16Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra16.selectAll()
                .where { Quadra16.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra16Dto(
                        id = it[Quadra16.id],
                        routeID = it[Quadra16.routeID],
                        staCode = it[Quadra16.staCode],
                        fromStaCode = it[Quadra16.fromStaCode],
                        toStaCode = it[Quadra16.toStaCode],
                        staName = it[Quadra16.staName],
                    )
                }
        }
    }
}
