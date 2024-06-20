package net.konohana.sakuya.yoshun.models.faredist.next

import org.jetbrains.exposed.sql.Table

object Next03FareDist : Table("next03_faredist") {
    val id = integer("id").autoIncrement()
    val routeID = varchar("route_id", length = 20)
    val staCode = varchar("sta_code", length = 20)
    val staName = varchar("sta_name", length = 20)
    val strtPtStaCode = varchar("strt_pt_sta_code", length = 20)
    val distance = double("distance")
}
