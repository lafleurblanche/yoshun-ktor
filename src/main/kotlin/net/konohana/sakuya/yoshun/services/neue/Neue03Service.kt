package net.konohana.sakuya.yoshun.services.neue

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.neue.Neue03Dto
import net.konohana.sakuya.yoshun.models.neue.Neue03
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Neue03Service {
    private fun resultRowNeue03(row: ResultRow) = Neue03Dto(
        id = row[Neue03.id],
        routeID = row[Neue03.routeID],
        fromStaCode = row[Neue03.fromStaCode],
        toStaCode = row[Neue03.toStaCode],
        staCode = row[Neue03.staCode],
        staName = row[Neue03.staName],
    )

    suspend fun getNeue03(): List<Neue03Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Neue03.selectAll().map(::resultRowNeue03)
        }
    }
}
