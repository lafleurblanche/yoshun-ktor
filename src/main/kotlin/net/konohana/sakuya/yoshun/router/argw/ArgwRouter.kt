package net.konohana.sakuya.yoshun.router.argw

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import net.konohana.sakuya.yoshun.controller.argw.Argw01Controller
import org.koin.ktor.ext.inject

fun Route.argwRouter() {
    val argw01Controller by inject<Argw01Controller>()
    route("cerisier") {
        route("argw01") {
            get {
                call.respond(argw01Controller.getArgw01StaList())
            }
        }
        route("argw01") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val argw01StaData = argw01Controller.getArgw01StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(argw01StaData)
                }
            }
        }
    }
}
