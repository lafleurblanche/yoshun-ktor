package net.konohana.sakuya.yoshun.models.gate

import org.jetbrains.exposed.sql.Table


object Gate01 : Table("gate_gt01") {
    val id = integer("id").autoIncrement()
    val routeID = varchar("route_id", length = 20)
    val staCode = varchar("sta_code", length = 20)
    val fromStaCode = varchar("from_sta_code", length = 20)
    val toStaCode = varchar("to_sta_code", length = 20)
    val staName = varchar("sta_name", length = 20)
}
