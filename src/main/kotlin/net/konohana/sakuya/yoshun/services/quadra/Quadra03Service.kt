package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra03Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra03FrontendDto
import net.konohana.sakuya.yoshun.models.quadra.Quadra03
import net.konohana.sakuya.yoshun.models.routes.QuadraRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Quadra03Service {
    private fun resultRowQuadra03(row: ResultRow) = Quadra03Dto(
        id = row[Quadra03.id],
        routeID = row[Quadra03.routeID],
        fromStaCode = row[Quadra03.fromStaCode],
        toStaCode = row[Quadra03.toStaCode],
        staCode = row[Quadra03.staCode],
        staName = row[Quadra03.staName],
    )

    private fun resultRowQuadra03Frontend(row: ResultRow): Quadra03FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Quadra03.staName]
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
        return Quadra03FrontendDto(
            id = row[Quadra03.id],
            viaRouteName = viaRouteName,
            staCode = row[Quadra03.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getQuadra03Frontend(): List<Quadra03FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra03
                // LEFT JOIN を使用して QuadraRoutes テーブルと結合
                .leftJoin(
                    QuadraRoutes,
                    { Quadra03.routeID },
                    { QuadraRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowQuadra03Frontend)
        }
    }

    suspend fun getQuadra03(): List<Quadra03Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra03.selectAll().map(::resultRowQuadra03)
        }
    }

    suspend fun getQuadra03ByStaCode(staCode: String): Quadra03Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra03.selectAll()
                .where { Quadra03.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra03Dto(
                        id = it[Quadra03.id],
                        routeID = it[Quadra03.routeID],
                        staCode = it[Quadra03.staCode],
                        fromStaCode = it[Quadra03.fromStaCode],
                        toStaCode = it[Quadra03.toStaCode],
                        staName = it[Quadra03.staName],
                    )
                }
        }
    }
}
