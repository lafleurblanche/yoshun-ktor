package net.konohana.sakuya.yoshun.router.enju

import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import net.konohana.sakuya.yoshun.controller.enju.Enju01Controller
import org.koin.ktor.ext.inject

fun Route.enjuRouter() {
    val enju01Controller by inject<Enju01Controller>()
    route("enju01") {
        get {
            call.respond(enju01Controller.getEnju01StaList())
        }
    }
}
