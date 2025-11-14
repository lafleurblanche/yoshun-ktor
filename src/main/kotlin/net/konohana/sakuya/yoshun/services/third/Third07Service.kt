package net.konohana.sakuya.yoshun.services.third

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.third.Third07Dto
import net.konohana.sakuya.yoshun.dtos.third.Third07FrontendDto
import net.konohana.sakuya.yoshun.models.routes.ThirdRoutes
import net.konohana.sakuya.yoshun.models.third.Third07
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Third07Service {
    private fun resultRowThird07(row: ResultRow) = Third07Dto(
        id = row[Third07.id],
        routeID = row[Third07.routeID],
        fromStaCode = row[Third07.fromStaCode],
        toStaCode = row[Third07.toStaCode],
        staCode = row[Third07.staCode],
        staName = row[Third07.staName],
    )

    private fun resultRowThird07Frontend(row: ResultRow): Third07FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Third07.staName]
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
        val viaRouteName = row.getOrNull(ThirdRoutes.viaRouteName) ?: ""

        // 3. Frontend DTOの生成
        return Third07FrontendDto(
            id = row[Third07.id],
            viaRouteName = viaRouteName,
            staCode = row[Third07.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getThird07Frontend(): List<Third07FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Third07
                // LEFT JOIN を使用して ThirdRoutes テーブルと結合
                .leftJoin(
                    ThirdRoutes,
                    { Third07.routeID },
                    { ThirdRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowThird07Frontend)
        }
    }

    suspend fun getThird07(): List<Third07Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Third07.selectAll().map(::resultRowThird07)
        }
    }

    suspend fun getThird07ByStaCode(staCode: String): Third07Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Third07.selectAll()
                .where { Third07.staCode eq staCode }
                .singleOrNull()?.let {
                    Third07Dto(
                        id = it[Third07.id],
                        routeID = it[Third07.routeID],
                        staCode = it[Third07.staCode],
                        fromStaCode = it[Third07.fromStaCode],
                        toStaCode = it[Third07.toStaCode],
                        staName = it[Third07.staName],
                    )
                }
        }
    }
}
