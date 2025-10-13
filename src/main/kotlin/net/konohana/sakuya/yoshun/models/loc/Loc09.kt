package net.konohana.sakuya.yoshun.models.loc

import org.jetbrains.exposed.sql.Table

/**
 * ## LOC09モデル
 * * 椎坂総線梅屋町線
 * @author lafleurblanche
 */
object Loc09 : Table("loc_lc09") {
    val id = integer("id").autoIncrement()
    val routeID = varchar("route_id", length = 20)
    val staCode = varchar("sta_code", length = 20)
    val fromStaCode = varchar("from_sta_code", length = 20)
    val toStaCode = varchar("to_sta_code", length = 20)
    val staName = varchar("sta_name", length = 20)
}
