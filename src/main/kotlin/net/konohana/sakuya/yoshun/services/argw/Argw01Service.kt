package net.konohana.sakuya.yoshun.services.argw

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.argw.Argw01Dto
import net.konohana.sakuya.yoshun.models.argw.Argw01
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Argw01Service {
    private fun resultRowArgw01(row: ResultRow) = Argw01Dto(
        id = row[Argw01.id],
        routeID = row[Argw01.routeID],
        fromStaCode = row[Argw01.fromStaCode],
        toStaCode = row[Argw01.toStaCode],
        staCode = row[Argw01.staCode],
        staName = row[Argw01.staName],
    )

    suspend fun getArgw01(): List<Argw01Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Argw01.selectAll().map(::resultRowArgw01)
        }
    }
}
