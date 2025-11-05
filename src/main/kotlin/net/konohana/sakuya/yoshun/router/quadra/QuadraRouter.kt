package net.konohana.sakuya.yoshun.router.quadra

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import kotlin.getValue
import net.konohana.sakuya.yoshun.controller.quadra.Quadra01Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra02Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra03Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra04Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra05Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra06Controller
import org.koin.ktor.ext.inject

fun Route.quadraRouter() {
    val quadra01Controller by inject<Quadra01Controller>()
    val quadra02Controller by inject<Quadra02Controller>()
    val quadra03Controller by inject<Quadra03Controller>()
    val quadra04Controller by inject<Quadra04Controller>()
    val quadra05Controller by inject<Quadra05Controller>()
    val quadra06Controller by inject<Quadra06Controller>()
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
        route("quadra02") {
            get {
                call.respond(quadra02Controller.getQuadra02StaList())
            }
        }
        route("quadra02") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra02StaData = quadra02Controller.getQuadra02StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra02StaData)
                }
            }
        }
        route("quadra03") {
            get {
                call.respond(quadra03Controller.getQuadra03StaList())
            }
        }
        route("quadra03") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra03StaData = quadra03Controller.getQuadra03StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra03StaData)
                }
            }
        }
        route("quadra04") {
            get {
                call.respond(quadra04Controller.getQuadra04StaList())
            }
        }
        route("quadra04") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra04StaData = quadra04Controller.getQuadra04StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra04StaData)
                }
            }
        }
        route("quadra05") {
            get {
                call.respond(quadra05Controller.getQuadra05StaList())
            }
        }
        route("quadra05") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra05StaData = quadra05Controller.getQuadra05StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra05StaData)
                }
            }
        }
        route("quadra06") {
            get {
                call.respond(quadra06Controller.getQuadra06StaList())
            }
        }
        route("quadra06") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra06StaData = quadra06Controller.getQuadra06StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra06StaData)
                }
            }
        }
    }
}
