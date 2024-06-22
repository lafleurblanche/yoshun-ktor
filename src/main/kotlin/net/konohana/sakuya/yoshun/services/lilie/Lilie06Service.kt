package net.konohana.sakuya.yoshun.services.lilie

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.lilie.Lilie06Dto
import net.konohana.sakuya.yoshun.models.lilie.Lilie06
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Lilie06Service {
    private fun resultRowLilie06(row: ResultRow) = Lilie06Dto(
        id = row[Lilie06.id],
        routeID = row[Lilie06.routeID],
        fromStaCode = row[Lilie06.fromStaCode],
        toStaCode = row[Lilie06.toStaCode],
        staCode = row[Lilie06.staCode],
        staName = row[Lilie06.staName],
    )

    suspend fun getLilie06(): List<Lilie06Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Lilie06.selectAll().map(::resultRowLilie06)
        }
    }
}
