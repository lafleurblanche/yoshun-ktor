package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra18Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra18FrontendDto
import net.konohana.sakuya.yoshun.models.quadra.Quadra18
import net.konohana.sakuya.yoshun.models.routes.QuadraRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Quadra18Service {
    private fun resultRowQuadra18(row: ResultRow) = Quadra18Dto(
        id = row[Quadra18.id],
        routeID = row[Quadra18.routeID],
        fromStaCode = row[Quadra18.fromStaCode],
        toStaCode = row[Quadra18.toStaCode],
        staCode = row[Quadra18.staCode],
        staName = row[Quadra18.staName],
    )

    private fun resultRowQuadra18Frontend(row: ResultRow): Quadra18FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Quadra18.staName]
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
        return Quadra18FrontendDto(
            id = row[Quadra18.id],
            viaRouteName = viaRouteName,
            staCode = row[Quadra18.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getQuadra18Frontend(): List<Quadra18FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra18
                // LEFT JOIN を使用して QuadraRoutes テーブルと結合
                .leftJoin(
                    QuadraRoutes,
                    { Quadra18.routeID },
                    { QuadraRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowQuadra18Frontend)
        }
    }

    suspend fun getQuadra18(): List<Quadra18Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra18.selectAll().map(::resultRowQuadra18)
        }
    }

    suspend fun getQuadra18ByStaCode(staCode: String): Quadra18Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra18.selectAll()
                .where { Quadra18.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra18Dto(
                        id = it[Quadra18.id],
                        routeID = it[Quadra18.routeID],
                        staCode = it[Quadra18.staCode],
                        fromStaCode = it[Quadra18.fromStaCode],
                        toStaCode = it[Quadra18.toStaCode],
                        staName = it[Quadra18.staName],
                    )
                }
        }
    }
}
