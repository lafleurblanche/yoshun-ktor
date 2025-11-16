package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra20Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra20FrontendDto
import net.konohana.sakuya.yoshun.models.quadra.Quadra20
import net.konohana.sakuya.yoshun.models.routes.QuadraRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Quadra20Service {
    private fun resultRowQuadra20(row: ResultRow) = Quadra20Dto(
        id = row[Quadra20.id],
        routeID = row[Quadra20.routeID],
        fromStaCode = row[Quadra20.fromStaCode],
        toStaCode = row[Quadra20.toStaCode],
        staCode = row[Quadra20.staCode],
        staName = row[Quadra20.staName],
    )

    private fun resultRowQuadra20Frontend(row: ResultRow): Quadra20FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Quadra20.staName]
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
        return Quadra20FrontendDto(
            id = row[Quadra20.id],
            viaRouteName = viaRouteName,
            staCode = row[Quadra20.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getQuadra20Frontend(): List<Quadra20FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra20
                // LEFT JOIN を使用して QuadraRoutes テーブルと結合
                .leftJoin(
                    QuadraRoutes,
                    { Quadra20.routeID },
                    { QuadraRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowQuadra20Frontend)
        }
    }

    suspend fun getQuadra20(): List<Quadra20Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra20.selectAll().map(::resultRowQuadra20)
        }
    }

    suspend fun getQuadra20ByStaCode(staCode: String): Quadra20Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra20.selectAll()
                .where { Quadra20.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra20Dto(
                        id = it[Quadra20.id],
                        routeID = it[Quadra20.routeID],
                        staCode = it[Quadra20.staCode],
                        fromStaCode = it[Quadra20.fromStaCode],
                        toStaCode = it[Quadra20.toStaCode],
                        staName = it[Quadra20.staName],
                    )
                }
        }
    }
}
