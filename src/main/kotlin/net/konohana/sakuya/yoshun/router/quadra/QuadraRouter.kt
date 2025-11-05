package net.konohana.sakuya.yoshun.router.quadra

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import kotlin.getValue
import net.konohana.sakuya.yoshun.controller.quadra.Quadra01Controller
import org.koin.ktor.ext.inject

fun Route.quadraRouter() {
    val quadra01Controller by inject<Quadra01Controller>()
    route("cerisier") {
        route("quadra01") {
            get {
                call.respond(quadra01Controller.getQuadra01StaList())
            }
        }
        route("quadra01") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra01StaData = quadra01Controller.getQuadra01StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra01StaData)
                }
            }
        }
    }
}
