package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra26Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra26FrontendDto
import net.konohana.sakuya.yoshun.models.quadra.Quadra26
import net.konohana.sakuya.yoshun.models.routes.QuadraRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Quadra26Service {
    private fun resultRowQuadra26(row: ResultRow) = Quadra26Dto(
        id = row[Quadra26.id],
        routeID = row[Quadra26.routeID],
        fromStaCode = row[Quadra26.fromStaCode],
        toStaCode = row[Quadra26.toStaCode],
        staCode = row[Quadra26.staCode],
        staName = row[Quadra26.staName],
    )

    private fun resultRowQuadra26Frontend(row: ResultRow): Quadra26FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Quadra26.staName]
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
        return Quadra26FrontendDto(
            id = row[Quadra26.id],
            viaRouteName = viaRouteName,
            staCode = row[Quadra26.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getQuadra26Frontend(): List<Quadra26FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra26
                // LEFT JOIN を使用して QuadraRoutes テーブルと結合
                .leftJoin(
                    QuadraRoutes,
                    { Quadra26.routeID },
                    { QuadraRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowQuadra26Frontend)
        }
    }

    suspend fun getQuadra26(): List<Quadra26Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra26.selectAll().map(::resultRowQuadra26)
        }
    }

    suspend fun getQuadra26ByStaCode(staCode: String): Quadra26Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra26.selectAll()
                .where { Quadra26.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra26Dto(
                        id = it[Quadra26.id],
                        routeID = it[Quadra26.routeID],
                        staCode = it[Quadra26.staCode],
                        fromStaCode = it[Quadra26.fromStaCode],
                        toStaCode = it[Quadra26.toStaCode],
                        staName = it[Quadra26.staName],
                    )
                }
        }
    }
}
