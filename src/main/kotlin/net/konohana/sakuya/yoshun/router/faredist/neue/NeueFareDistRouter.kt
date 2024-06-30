package net.konohana.sakuya.yoshun.router.faredist.neue

import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import net.konohana.sakuya.yoshun.controller.faredist.neue.Neue01FareDistController
import net.konohana.sakuya.yoshun.controller.faredist.neue.Neue02FareDistController
import org.koin.ktor.ext.inject

fun Route.neueFareDistRouter() {
    val neue01FareDistController by inject<Neue01FareDistController>()
    val neue02FareDistController by inject<Neue02FareDistController>()
    route("neue01faredist") {
        get {
            call.respond(neue01FareDistController.getNeue01FareDistList())
        }
    }
    route("neue02faredist") {
        get {
            call.respond(neue02FareDistController.getNeue02FareDistList())
        }
    }
}
