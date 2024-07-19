package net.konohana.sakuya.yoshun.setup.models.hnki

import org.jetbrains.exposed.sql.Table

/**
 * ## Hnki03モデル
 * ひのき管理区柝谷線
 * @author lafleurblanche
 */
object Hnki03 : Table("hnki_hn03") {
    val id = integer("id").autoIncrement()
    val routeID = varchar("route_id", length = 20)
    val staCode = varchar("sta_code", length = 20)
    val fromStaCode = varchar("from_sta_code", length = 20)
    val toStaCode = varchar("to_sta_code", length = 20)
    val staName = varchar("sta_name", length = 20)
}
