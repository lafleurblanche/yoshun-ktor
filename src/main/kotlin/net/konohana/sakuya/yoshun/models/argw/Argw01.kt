package net.konohana.sakuya.yoshun.models.argw

import org.jetbrains.exposed.sql.Table

/**
 * ## AreaGW01モデル
 * 支線連絡系統梼谷線
 * @author lafleurblanche
 */
object Argw01 : Table("argw_ar01") {
    val id = integer("id").autoIncrement()
    val routeID = varchar("route_id", length = 20)
    val staCode = varchar("sta_code", length = 20)
    val fromStaCode = varchar("from_sta_code", length = 20)
    val toStaCode = varchar("to_sta_code", length = 20)
    val staName = varchar("sta_name", length = 20)
}
