package net.konohana.sakuya.yoshun.models.faredist.loc

import org.jetbrains.exposed.sql.Table

object Loc08FareDist : Table("loc08_faredist") {
    val id = integer("id").autoIncrement()
    val routeID = varchar("route_id", length = 20)
    val staCode = varchar("sta_code", length = 20)
    val staName = varchar("sta_name", length = 20)
    val strtPtStaCode = varchar("strt_pt_sta_code", length = 20)
    val distance = double("distance")
}
