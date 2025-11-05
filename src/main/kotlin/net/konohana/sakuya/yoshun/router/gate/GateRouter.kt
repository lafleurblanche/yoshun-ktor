package net.konohana.sakuya.yoshun.router.gate

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import kotlin.getValue
import net.konohana.sakuya.yoshun.controller.gate.Gate01Controller
import net.konohana.sakuya.yoshun.controller.gate.Gate02Controller
import net.konohana.sakuya.yoshun.controller.gate.Gate03Controller
import net.konohana.sakuya.yoshun.controller.gate.Gate04Controller
import net.konohana.sakuya.yoshun.controller.gate.Gate05Controller
import org.koin.ktor.ext.inject

fun Route.gateRouter() {
    val gate01Controller by inject<Gate01Controller>()
    val gate02Controller by inject<Gate02Controller>()
    val gate03Controller by inject<Gate03Controller>()
    val gate04Controller by inject<Gate04Controller>()
    val gate05Controller by inject<Gate05Controller>()
    route("cerisier") {
        route("gate01") {
            get {
                call.respond(gate01Controller.getGate01StaList())
            }
        }
        route("gate01") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val gate01StaData = gate01Controller.getGate01StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(gate01StaData)
                }
            }
        }
        route("gate02") {
            get {
                call.respond(gate02Controller.getGate02StaList())
            }
        }
        route("gate02") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val gate02StaData = gate02Controller.getGate02StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(gate02StaData)
                }
            }
        }
        route("gate03") {
            get {
                call.respond(gate03Controller.getGate03StaList())
            }
        }
        route("gate03") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val gate03StaData = gate03Controller.getGate03StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(gate03StaData)
                }
            }
        }
        route("gate04") {
            get {
                call.respond(gate04Controller.getGate04StaList())
            }
        }
        route("gate04") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val gate04StaData = gate04Controller.getGate04StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(gate04StaData)
                }
            }
        }
        route("gate05") {
            get {
                call.respond(gate05Controller.getGate05StaList())
            }
        }
        route("gate05") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val gate05StaData = gate05Controller.getGate05StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(gate05StaData)
                }
            }
        }
    }
}
