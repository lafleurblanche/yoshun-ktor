package net.konohana.sakuya.yoshun.services.enju

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.enju.Enju01Dto
import net.konohana.sakuya.yoshun.models.enju.Enju01
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Enju01Service {
    private fun resultRowEnju01(row: ResultRow) = Enju01Dto(
        id = row[Enju01.id],
        routeID = row[Enju01.routeID],
        fromStaCode = row[Enju01.fromStaCode],
        toStaCode = row[Enju01.toStaCode],
        staCode = row[Enju01.staCode],
        staName = row[Enju01.staName],
    )

    suspend fun getEnju01(): List<Enju01Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Enju01.selectAll().map(::resultRowEnju01)
        }
    }
}
