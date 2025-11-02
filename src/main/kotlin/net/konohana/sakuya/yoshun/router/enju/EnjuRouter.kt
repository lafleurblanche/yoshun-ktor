package net.konohana.sakuya.yoshun.router.enju

import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import net.konohana.sakuya.yoshun.controller.enju.Enju01Controller
import net.konohana.sakuya.yoshun.controller.enju.Enju02Controller
import net.konohana.sakuya.yoshun.controller.enju.Enju03Controller
import org.koin.ktor.ext.inject

fun Route.enjuRouter() {
    val enju01Controller by inject<Enju01Controller>()
    val enju02Controller by inject<Enju02Controller>()
    val enju03Controller by inject<Enju03Controller>()
    route("cerisier") {
        route("enju01") {
            get {
                call.respond(enju01Controller.getEnju01StaList())
            }
        }
        route("enju02") {
            get {
                call.respond(enju02Controller.getEnju02StaList())
            }
        }
        route("enju03") {
            get {
                call.respond(enju03Controller.getEnju03StaList())
            }
        }
    }
}
