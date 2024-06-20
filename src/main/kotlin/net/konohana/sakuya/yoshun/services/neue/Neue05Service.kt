package net.konohana.sakuya.yoshun.services.neue

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.neue.Neue05Dto
import net.konohana.sakuya.yoshun.models.neue.Neue05
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Neue05Service {
    private fun resultRowNeue05(row: ResultRow) = Neue05Dto(
        id = row[Neue05.id],
        routeID = row[Neue05.routeID],
        fromStaCode = row[Neue05.fromStaCode],
        toStaCode = row[Neue05.toStaCode],
        staCode = row[Neue05.staCode],
        staName = row[Neue05.staName],
    )

    suspend fun getNeue05(): List<Neue05Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Neue05.selectAll().map(::resultRowNeue05)
        }
    }
}
