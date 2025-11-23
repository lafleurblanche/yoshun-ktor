package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra25Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra25FrontendDto
import net.konohana.sakuya.yoshun.models.quadra.Quadra25
import net.konohana.sakuya.yoshun.models.routes.QuadraRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Quadra25Service {
    private fun resultRowQuadra25(row: ResultRow) = Quadra25Dto(
        id = row[Quadra25.id],
        routeID = row[Quadra25.routeID],
        fromStaCode = row[Quadra25.fromStaCode],
        toStaCode = row[Quadra25.toStaCode],
        staCode = row[Quadra25.staCode],
        staName = row[Quadra25.staName],
    )

    private fun resultRowQuadra25Frontend(row: ResultRow): Quadra25FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Quadra25.staName]
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
        return Quadra25FrontendDto(
            id = row[Quadra25.id],
            viaRouteName = viaRouteName,
            staCode = row[Quadra25.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getQuadra25Frontend(): List<Quadra25FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra25
                // LEFT JOIN を使用して QuadraRoutes テーブルと結合
                .leftJoin(
                    QuadraRoutes,
                    { Quadra25.routeID },
                    { QuadraRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowQuadra25Frontend)
        }
    }

    suspend fun getQuadra25(): List<Quadra25Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra25.selectAll().map(::resultRowQuadra25)
        }
    }

    suspend fun getQuadra25ByStaCode(staCode: String): Quadra25Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra25.selectAll()
                .where { Quadra25.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra25Dto(
                        id = it[Quadra25.id],
                        routeID = it[Quadra25.routeID],
                        staCode = it[Quadra25.staCode],
                        fromStaCode = it[Quadra25.fromStaCode],
                        toStaCode = it[Quadra25.toStaCode],
                        staName = it[Quadra25.staName],
                    )
                }
        }
    }
}
