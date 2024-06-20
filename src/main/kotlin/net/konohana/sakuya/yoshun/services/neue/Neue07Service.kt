package net.konohana.sakuya.yoshun.services.neue

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.neue.Neue07Dto
import net.konohana.sakuya.yoshun.models.neue.Neue07
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Neue07Service {
    private fun resultRowNeue07(row: ResultRow) = Neue07Dto(
        id = row[Neue07.id],
        routeID = row[Neue07.routeID],
        fromStaCode = row[Neue07.fromStaCode],
        toStaCode = row[Neue07.toStaCode],
        staCode = row[Neue07.staCode],
        staName = row[Neue07.staName],
    )

    suspend fun getNeue07(): List<Neue07Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Neue07.selectAll().map(::resultRowNeue07)
        }
    }
}
