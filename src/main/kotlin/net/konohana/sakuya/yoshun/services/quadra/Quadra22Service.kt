package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra22Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra22FrontendDto
import net.konohana.sakuya.yoshun.models.quadra.Quadra22
import net.konohana.sakuya.yoshun.models.routes.QuadraRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Quadra22Service {
    private fun resultRowQuadra22(row: ResultRow) = Quadra22Dto(
        id = row[Quadra22.id],
        routeID = row[Quadra22.routeID],
        fromStaCode = row[Quadra22.fromStaCode],
        toStaCode = row[Quadra22.toStaCode],
        staCode = row[Quadra22.staCode],
        staName = row[Quadra22.staName],
    )

    private fun resultRowQuadra22Frontend(row: ResultRow): Quadra22FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Quadra22.staName]
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
        return Quadra22FrontendDto(
            id = row[Quadra22.id],
            viaRouteName = viaRouteName,
            staCode = row[Quadra22.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getQuadra22Frontend(): List<Quadra22FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra22
                // LEFT JOIN を使用して QuadraRoutes テーブルと結合
                .leftJoin(
                    QuadraRoutes,
                    { Quadra22.routeID },
                    { QuadraRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowQuadra22Frontend)
        }
    }

    suspend fun getQuadra22(): List<Quadra22Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra22.selectAll().map(::resultRowQuadra22)
        }
    }

    suspend fun getQuadra22ByStaCode(staCode: String): Quadra22Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra22.selectAll()
                .where { Quadra22.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra22Dto(
                        id = it[Quadra22.id],
                        routeID = it[Quadra22.routeID],
                        staCode = it[Quadra22.staCode],
                        fromStaCode = it[Quadra22.fromStaCode],
                        toStaCode = it[Quadra22.toStaCode],
                        staName = it[Quadra22.staName],
                    )
                }
        }
    }
}
