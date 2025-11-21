package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra23Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra23FrontendDto
import net.konohana.sakuya.yoshun.models.quadra.Quadra23
import net.konohana.sakuya.yoshun.models.routes.QuadraRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Quadra23Service {
    private fun resultRowQuadra23(row: ResultRow) = Quadra23Dto(
        id = row[Quadra23.id],
        routeID = row[Quadra23.routeID],
        fromStaCode = row[Quadra23.fromStaCode],
        toStaCode = row[Quadra23.toStaCode],
        staCode = row[Quadra23.staCode],
        staName = row[Quadra23.staName],
    )

    private fun resultRowQuadra23Frontend(row: ResultRow): Quadra23FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Quadra23.staName]
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
        return Quadra23FrontendDto(
            id = row[Quadra23.id],
            viaRouteName = viaRouteName,
            staCode = row[Quadra23.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getQuadra23Frontend(): List<Quadra23FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra23
                // LEFT JOIN を使用して QuadraRoutes テーブルと結合
                .leftJoin(
                    QuadraRoutes,
                    { Quadra23.routeID },
                    { QuadraRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowQuadra23Frontend)
        }
    }

    suspend fun getQuadra23(): List<Quadra23Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra23.selectAll().map(::resultRowQuadra23)
        }
    }

    suspend fun getQuadra23ByStaCode(staCode: String): Quadra23Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra23.selectAll()
                .where { Quadra23.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra23Dto(
                        id = it[Quadra23.id],
                        routeID = it[Quadra23.routeID],
                        staCode = it[Quadra23.staCode],
                        fromStaCode = it[Quadra23.fromStaCode],
                        toStaCode = it[Quadra23.toStaCode],
                        staName = it[Quadra23.staName],
                    )
                }
        }
    }
}
