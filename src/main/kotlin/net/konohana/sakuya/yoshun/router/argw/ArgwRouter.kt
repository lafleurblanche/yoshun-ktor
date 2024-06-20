package net.konohana.sakuya.yoshun.router.argw

import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import net.konohana.sakuya.yoshun.controller.argw.Argw01Controller
import org.koin.ktor.ext.inject

fun Route.argwRouter() {
    val argw01Controller by inject<Argw01Controller>()
    route("argw01") {
        get {
            call.respond(argw01Controller.getArgw01StaList())
        }
    }
}
