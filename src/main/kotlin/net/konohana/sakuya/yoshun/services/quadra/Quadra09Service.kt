package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra09Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra09FrontendDto
import net.konohana.sakuya.yoshun.models.quadra.Quadra09
import net.konohana.sakuya.yoshun.models.routes.QuadraRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Quadra09Service {
    private fun resultRowQuadra09(row: ResultRow) = Quadra09Dto(
        id = row[Quadra09.id],
        routeID = row[Quadra09.routeID],
        fromStaCode = row[Quadra09.fromStaCode],
        toStaCode = row[Quadra09.toStaCode],
        staCode = row[Quadra09.staCode],
        staName = row[Quadra09.staName],
    )

    private fun resultRowQuadra09Frontend(row: ResultRow): Quadra09FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Quadra09.staName]
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
        return Quadra09FrontendDto(
            id = row[Quadra09.id],
            viaRouteName = viaRouteName,
            staCode = row[Quadra09.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getQuadra09Frontend(): List<Quadra09FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra09
                // LEFT JOIN を使用して QuadraRoutes テーブルと結合
                .leftJoin(
                    QuadraRoutes,
                    { Quadra09.routeID },
                    { QuadraRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowQuadra09Frontend)
        }
    }

    suspend fun getQuadra09(): List<Quadra09Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra09.selectAll().map(::resultRowQuadra09)
        }
    }

    suspend fun getQuadra09ByStaCode(staCode: String): Quadra09Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra09.selectAll()
                .where { Quadra09.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra09Dto(
                        id = it[Quadra09.id],
                        routeID = it[Quadra09.routeID],
                        staCode = it[Quadra09.staCode],
                        fromStaCode = it[Quadra09.fromStaCode],
                        toStaCode = it[Quadra09.toStaCode],
                        staName = it[Quadra09.staName],
                    )
                }
        }
    }
}
