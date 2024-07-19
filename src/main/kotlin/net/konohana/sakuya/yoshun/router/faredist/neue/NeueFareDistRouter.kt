package net.konohana.sakuya.yoshun.router.faredist.neue

import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import net.konohana.sakuya.yoshun.controller.faredist.neue.Neue01FareDistController
import net.konohana.sakuya.yoshun.controller.faredist.neue.Neue02FareDistController
import net.konohana.sakuya.yoshun.controller.faredist.neue.Neue03FareDistController
import net.konohana.sakuya.yoshun.controller.faredist.neue.Neue04FareDistController
import org.koin.ktor.ext.inject

fun Route.neueFareDistRouter() {
    val neue01FareDistController by inject<Neue01FareDistController>()
    val neue02FareDistController by inject<Neue02FareDistController>()
    val neue03FareDistController by inject<Neue03FareDistController>()
    val neue04FareDistController by inject<Neue04FareDistController>()
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
    route("neue03faredist") {
        get {
            call.respond(neue03FareDistController.getNeue03FareDistList())
        }
    }
    route("neue04faredist") {
        get {
            call.respond(neue04FareDistController.getNeue04FareDistList())
        }
    }
}
