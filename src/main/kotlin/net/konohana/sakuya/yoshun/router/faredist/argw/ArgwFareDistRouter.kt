package net.konohana.sakuya.yoshun.router.faredist.argw

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import net.konohana.sakuya.yoshun.controller.faredist.argw.Argw01FareDistController
import org.koin.ktor.ext.inject

fun Route.argwFareDistRouter() {
    val argw01FareDistController by inject<Argw01FareDistController>()
    route("argw01faredist") {
        get {
            call.respond(argw01FareDistController.getArgw01FareDistList())
        }
    }
    route("argw01faredist") {
        route("/{staCode}") {
            get {
                val staCode = call.parameters["staCode"]?: run {
                    return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                }
                val argw01FaraDist = argw01FareDistController.getArgw01FareDistListByStaCode(staCode = staCode) ?: run {
                    return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                }
                call.respond(argw01FaraDist)
            }
        }
    }
}
