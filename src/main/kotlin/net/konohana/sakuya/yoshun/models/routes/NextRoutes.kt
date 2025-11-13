package net.konohana.sakuya.yoshun.models.routes

import org.jetbrains.exposed.sql.Table

object NextRoutes : Table("next_routes") {
    val routeID = varchar("route_id", 10).uniqueIndex()
    val viaRouteName = varchar("via_route_name", 100)
}
