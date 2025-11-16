package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra19Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra19FrontendDto
import net.konohana.sakuya.yoshun.models.quadra.Quadra19
import net.konohana.sakuya.yoshun.models.routes.QuadraRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Quadra19Service {
    private fun resultRowQuadra19(row: ResultRow) = Quadra19Dto(
        id = row[Quadra19.id],
        routeID = row[Quadra19.routeID],
        fromStaCode = row[Quadra19.fromStaCode],
        toStaCode = row[Quadra19.toStaCode],
        staCode = row[Quadra19.staCode],
        staName = row[Quadra19.staName],
    )

    private fun resultRowQuadra19Frontend(row: ResultRow): Quadra19FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Quadra19.staName]
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
        return Quadra19FrontendDto(
            id = row[Quadra19.id],
            viaRouteName = viaRouteName,
            staCode = row[Quadra19.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getQuadra19Frontend(): List<Quadra19FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra19
                // LEFT JOIN を使用して QuadraRoutes テーブルと結合
                .leftJoin(
                    QuadraRoutes,
                    { Quadra19.routeID },
                    { QuadraRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowQuadra19Frontend)
        }
    }

    suspend fun getQuadra19(): List<Quadra19Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra19.selectAll().map(::resultRowQuadra19)
        }
    }

    suspend fun getQuadra19ByStaCode(staCode: String): Quadra19Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra19.selectAll()
                .where { Quadra19.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra19Dto(
                        id = it[Quadra19.id],
                        routeID = it[Quadra19.routeID],
                        staCode = it[Quadra19.staCode],
                        fromStaCode = it[Quadra19.fromStaCode],
                        toStaCode = it[Quadra19.toStaCode],
                        staName = it[Quadra19.staName],
                    )
                }
        }
    }
}
