package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra10Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra10FrontendDto
import net.konohana.sakuya.yoshun.models.quadra.Quadra10
import net.konohana.sakuya.yoshun.models.routes.QuadraRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Quadra10Service {
    private fun resultRowQuadra10(row: ResultRow) = Quadra10Dto(
        id = row[Quadra10.id],
        routeID = row[Quadra10.routeID],
        fromStaCode = row[Quadra10.fromStaCode],
        toStaCode = row[Quadra10.toStaCode],
        staCode = row[Quadra10.staCode],
        staName = row[Quadra10.staName],
    )

    private fun resultRowQuadra10Frontend(row: ResultRow): Quadra10FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Quadra10.staName]
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
        return Quadra10FrontendDto(
            id = row[Quadra10.id],
            viaRouteName = viaRouteName,
            staCode = row[Quadra10.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getQuadra10Frontend(): List<Quadra10FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra10
                // LEFT JOIN を使用して QuadraRoutes テーブルと結合
                .leftJoin(
                    QuadraRoutes,
                    { Quadra10.routeID },
                    { QuadraRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowQuadra10Frontend)
        }
    }

    suspend fun getQuadra10(): List<Quadra10Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra10.selectAll().map(::resultRowQuadra10)
        }
    }

    suspend fun getQuadra10ByStaCode(staCode: String): Quadra10Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra10.selectAll()
                .where { Quadra10.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra10Dto(
                        id = it[Quadra10.id],
                        routeID = it[Quadra10.routeID],
                        staCode = it[Quadra10.staCode],
                        fromStaCode = it[Quadra10.fromStaCode],
                        toStaCode = it[Quadra10.toStaCode],
                        staName = it[Quadra10.staName],
                    )
                }
        }
    }
}
