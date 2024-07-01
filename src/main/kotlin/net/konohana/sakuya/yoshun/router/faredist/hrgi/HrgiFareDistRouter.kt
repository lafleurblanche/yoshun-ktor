package net.konohana.sakuya.yoshun.router.faredist.hrgi

import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import net.konohana.sakuya.yoshun.controller.faredist.hrgi.Hrgi01FareDistController
import org.koin.ktor.ext.inject

fun Route.hrgiFareDistRouter() {
    val hrgi01FareDistController by inject<Hrgi01FareDistController>()
    route("hrgi01faredist") {
        get {
            call.respond(hrgi01FareDistController.getHrgi01FareDistList())
        }
    }
}
