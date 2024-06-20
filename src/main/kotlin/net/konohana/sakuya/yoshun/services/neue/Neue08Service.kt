package net.konohana.sakuya.yoshun.services.neue

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.neue.Neue08Dto
import net.konohana.sakuya.yoshun.models.neue.Neue08
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Neue08Service {
    private fun resultRowNeue08(row: ResultRow) = Neue08Dto(
        id = row[Neue08.id],
        routeID = row[Neue08.routeID],
        fromStaCode = row[Neue08.fromStaCode],
        toStaCode = row[Neue08.toStaCode],
        staCode = row[Neue08.staCode],
        staName = row[Neue08.staName],
    )

    suspend fun getNeue08(): List<Neue08Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Neue08.selectAll().map(::resultRowNeue08)
        }
    }
}
