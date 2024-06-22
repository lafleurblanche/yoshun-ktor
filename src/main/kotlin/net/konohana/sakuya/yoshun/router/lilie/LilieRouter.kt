package net.konohana.sakuya.yoshun.router.lilie

import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import net.konohana.sakuya.yoshun.controller.lilie.Lilie01Controller
import net.konohana.sakuya.yoshun.controller.lilie.Lilie02Controller
import net.konohana.sakuya.yoshun.controller.lilie.Lilie03Controller
import net.konohana.sakuya.yoshun.controller.lilie.Lilie04Controller
import net.konohana.sakuya.yoshun.controller.lilie.Lilie05Controller
import net.konohana.sakuya.yoshun.controller.lilie.Lilie06Controller
import org.koin.ktor.ext.inject

fun Route.lilieRouter() {
    val lilie01Controller by inject<Lilie01Controller>()
    val lilie02Controller by inject<Lilie02Controller>()
    val lilie03Controller by inject<Lilie03Controller>()
    val lilie04Controller by inject<Lilie04Controller>()
    val lilie05Controller by inject<Lilie05Controller>()
    val lilie06Controller by inject<Lilie06Controller>()
    route("lilie01") {
        get {
            call.respond(lilie01Controller.getLilie01StaList())
        }
    }
    route("lilie02") {
        get {
            call.respond(lilie02Controller.getLilie02StaList())
        }
    }
    route("lilie03") {
        get {
            call.respond(lilie03Controller.getLilie03StaList())
        }
    }
    route("lilie04") {
        get {
            call.respond(lilie04Controller.getLilie04StaList())
        }
    }
    route("lilie05") {
        get {
            call.respond(lilie05Controller.getLilie05StaList())
        }
    }
    route("lilie06") {
        get {
            call.respond(lilie06Controller.getLilie06StaList())
        }
    }
}
