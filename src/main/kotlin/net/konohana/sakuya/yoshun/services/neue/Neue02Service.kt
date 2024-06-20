package net.konohana.sakuya.yoshun.services.neue

import net.konohana.sakuya.yoshun.db.KaedeDatabaseFactory
import net.konohana.sakuya.yoshun.dtos.neue.Neue02Dto
import net.konohana.sakuya.yoshun.models.neue.Neue02
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class Neue02Service {
    private fun resultRowNeue02(row: ResultRow) = Neue02Dto(
        id = row[Neue02.id],
        routeID = row[Neue02.routeID],
        fromStaCode = row[Neue02.fromStaCode],
        toStaCode = row[Neue02.toStaCode],
        staCode = row[Neue02.staCode],
        staName = row[Neue02.staName],
    )

    suspend fun getNeue02(): List<Neue02Dto> {
        return KaedeDatabaseFactory.dbQuery {
            Neue02.selectAll().map(::resultRowNeue02)
        }
    }
}
