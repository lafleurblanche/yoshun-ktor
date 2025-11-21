package net.konohana.sakuya.yoshun.services.quadra

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra21Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra21FrontendDto
import net.konohana.sakuya.yoshun.models.quadra.Quadra21
import net.konohana.sakuya.yoshun.models.routes.QuadraRoutes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Quadra21Service {
    private fun resultRowQuadra21(row: ResultRow) = Quadra21Dto(
        id = row[Quadra21.id],
        routeID = row[Quadra21.routeID],
        fromStaCode = row[Quadra21.fromStaCode],
        toStaCode = row[Quadra21.toStaCode],
        staCode = row[Quadra21.staCode],
        staName = row[Quadra21.staName],
    )

    private fun resultRowQuadra21Frontend(row: ResultRow): Quadra21FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Quadra21.staName]
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
        return Quadra21FrontendDto(
            id = row[Quadra21.id],
            viaRouteName = viaRouteName,
            staCode = row[Quadra21.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getQuadra21Frontend(): List<Quadra21FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra21
                // LEFT JOIN を使用して QuadraRoutes テーブルと結合
                .leftJoin(
                    QuadraRoutes,
                    { Quadra21.routeID },
                    { QuadraRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowQuadra21Frontend)
        }
    }

    suspend fun getQuadra21(): List<Quadra21Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Quadra21.selectAll().map(::resultRowQuadra21)
        }
    }

    suspend fun getQuadra21ByStaCode(staCode: String): Quadra21Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Quadra21.selectAll()
                .where { Quadra21.staCode eq staCode }
                .singleOrNull()?.let {
                    Quadra21Dto(
                        id = it[Quadra21.id],
                        routeID = it[Quadra21.routeID],
                        staCode = it[Quadra21.staCode],
                        fromStaCode = it[Quadra21.fromStaCode],
                        toStaCode = it[Quadra21.toStaCode],
                        staName = it[Quadra21.staName],
                    )
                }
        }
    }
}
