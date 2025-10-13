package net.konohana.sakuya.yoshun.models.loc

import org.jetbrains.exposed.sql.Table

/**
 * ## LOC05モデル
 * * 椎坂総線柃坂線
 * @author lafleurblanche
 */
object Loc05 : Table("loc_lc05") {
    val id = integer("id").autoIncrement()
    val routeID = varchar("route_id", length = 20)
    val staCode = varchar("sta_code", length = 20)
    val fromStaCode = varchar("from_sta_code", length = 20)
    val toStaCode = varchar("to_sta_code", length = 20)
    val staName = varchar("sta_name", length = 20)
}
