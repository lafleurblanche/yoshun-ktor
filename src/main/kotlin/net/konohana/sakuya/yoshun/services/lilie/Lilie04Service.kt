package net.konohana.sakuya.yoshun.services.lilie

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.lilie.Lilie04Dto
import net.konohana.sakuya.yoshun.models.lilie.Lilie04
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Lilie04Service {
    private fun resultRowLilie04(row: ResultRow) = Lilie04Dto(
        id = row[Lilie04.id],
        routeID = row[Lilie04.routeID],
        fromStaCode = row[Lilie04.fromStaCode],
        toStaCode = row[Lilie04.toStaCode],
        staCode = row[Lilie04.staCode],
        staName = row[Lilie04.staName],
    )

    suspend fun getLilie04(): List<Lilie04Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Lilie04.selectAll().map(::resultRowLilie04)
        }
    }
}
