package net.konohana.sakuya.yoshun.router.sample

import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import net.konohana.sakuya.yoshun.dtos.neue.Neue01Dto

fun Route.sampleNeueRoutes() {
    route("/api/neue") {
        get("/stations") {
            val station = Neue01Dto(
                id = 1,
                routeID = "CERISIER01",
                staCode = "TST01",
                fromStaCode = "TST01",
                toStaCode = "TST02",
                staName = "テスト駅"
            )
            call.respond(listOf(station))
        }
    }
}
