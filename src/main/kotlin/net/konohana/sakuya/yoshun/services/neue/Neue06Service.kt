package net.konohana.sakuya.yoshun.services.neue

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.neue.Neue06Dto
import net.konohana.sakuya.yoshun.models.neue.Neue06
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Neue06Service {
    private fun resultRowNeue06(row: ResultRow) = Neue06Dto(
        id = row[Neue06.id],
        routeID = row[Neue06.routeID],
        fromStaCode = row[Neue06.fromStaCode],
        toStaCode = row[Neue06.toStaCode],
        staCode = row[Neue06.staCode],
        staName = row[Neue06.staName],
    )

    suspend fun getNeue06(): List<Neue06Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Neue06.selectAll().map(::resultRowNeue06)
        }
    }
}
