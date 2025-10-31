package net.konohana.sakuya.yoshun.models.quadra

import org.jetbrains.exposed.sql.Table

/**
 * ## Quadra08モデル
 * @author lafleurblanche
 */
object Quadra08 : Table("quadra_qd08") {
    val id = integer("id").autoIncrement()
    val routeID = varchar("route_id", length = 20)
    val staCode = varchar("sta_code", length = 20)
    val fromStaCode = varchar("from_sta_code", length = 20)
    val toStaCode = varchar("to_sta_code", length = 20)
    val staName = varchar("sta_name", length = 20)
}
