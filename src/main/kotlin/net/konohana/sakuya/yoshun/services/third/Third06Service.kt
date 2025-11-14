package net.konohana.sakuya.yoshun.services.third

import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.MIN_LENGTH_FOR_SPLIT
import net.konohana.sakuya.yoshun.constants.CommonConst.Companion.SPLIT_LENGTH
import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.third.Third06Dto
import net.konohana.sakuya.yoshun.dtos.third.Third06FrontendDto
import net.konohana.sakuya.yoshun.models.routes.ThirdRoutes
import net.konohana.sakuya.yoshun.models.third.Third06
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll

class Third06Service {
    private fun resultRowThird06(row: ResultRow) = Third06Dto(
        id = row[Third06.id],
        routeID = row[Third06.routeID],
        fromStaCode = row[Third06.fromStaCode],
        toStaCode = row[Third06.toStaCode],
        staCode = row[Third06.staCode],
        staName = row[Third06.staName],
    )

    private fun resultRowThird06Frontend(row: ResultRow): Third06FrontendDto {
        // 1. staName 分割ロジック (前回の回答で確認済み)
        val staName = row[Third06.staName]
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
        return Third06FrontendDto(
            id = row[Third06.id],
            viaRouteName = viaRouteName,
            staCode = row[Third06.staCode],
            staName1 = staName1,
            staName2 = staName2,
        )
    }

    suspend fun getThird06Frontend(): List<Third06FrontendDto> {
        return KaedeDatabaseFactory.dbQuery {
            Third06
                // LEFT JOIN を使用して ThirdRoutes テーブルと結合
                .leftJoin(
                    ThirdRoutes,
                    { Third06.routeID },
                    { ThirdRoutes.routeID }
                )
                .selectAll()
                .map(::resultRowThird06Frontend)
        }
    }

    suspend fun getThird06(): List<Third06Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Third06.selectAll().map(::resultRowThird06)
        }
    }

    suspend fun getThird06ByStaCode(staCode: String): Third06Dto? {
        return KaedeDatabaseFactory.dbQuery {
            Third06.selectAll()
                .where { Third06.staCode eq staCode }
                .singleOrNull()?.let {
                    Third06Dto(
                        id = it[Third06.id],
                        routeID = it[Third06.routeID],
                        staCode = it[Third06.staCode],
                        fromStaCode = it[Third06.fromStaCode],
                        toStaCode = it[Third06.toStaCode],
                        staName = it[Third06.staName],
                    )
                }
        }
    }
}
