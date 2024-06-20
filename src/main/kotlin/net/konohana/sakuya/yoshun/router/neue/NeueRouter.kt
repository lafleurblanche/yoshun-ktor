package net.konohana.sakuya.yoshun.router.neue

import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import net.konohana.sakuya.yoshun.controller.neue.Neue01Controller
import net.konohana.sakuya.yoshun.controller.neue.Neue02Controller
import net.konohana.sakuya.yoshun.controller.neue.Neue03Controller
import net.konohana.sakuya.yoshun.controller.neue.Neue04Controller
import net.konohana.sakuya.yoshun.controller.neue.Neue05Controller
import net.konohana.sakuya.yoshun.controller.neue.Neue06Controller
import org.koin.ktor.ext.inject

fun Route.neueRouter() {
    val neue01Controller by inject<Neue01Controller>()
    val neue02Controller by inject<Neue02Controller>()
    val neue03Controller by inject<Neue03Controller>()
    val neue04Controller by inject<Neue04Controller>()
    val neue05Controller by inject<Neue05Controller>()
    val neue06Controller by inject<Neue06Controller>()
    route("neue01") {
        get {
            call.respond(neue01Controller.getNeue01StaList())
        }
    }
    route("neue02") {
        get {
            call.respond(neue02Controller.getNeue02StaList())
        }
    }
    route("neue03") {
        get {
            call.respond(neue03Controller.getNeue03StaList())
        }
    }
    route("neue04") {
        get {
            call.respond(neue04Controller.getNeue04StaList())
        }
    }
    route("neue05") {
        get {
            call.respond(neue05Controller.getNeue05StaList())
        }
    }
    route("neue06") {
        get {
            call.respond(neue06Controller.getNeue06StaList())
        }
    }
}
