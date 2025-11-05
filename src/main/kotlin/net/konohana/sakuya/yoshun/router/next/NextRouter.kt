package net.konohana.sakuya.yoshun.router.next

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import net.konohana.sakuya.yoshun.controller.next.Next01Controller
import net.konohana.sakuya.yoshun.controller.next.Next02Controller
import net.konohana.sakuya.yoshun.controller.next.Next03Controller
import net.konohana.sakuya.yoshun.controller.next.Next04Controller
import net.konohana.sakuya.yoshun.controller.next.Next05Controller

import org.koin.ktor.ext.inject

fun Route.nextRouter() {
    val next01Controller by inject<Next01Controller>()
    val next02Controller by inject<Next02Controller>()
    val next03Controller by inject<Next03Controller>()
    val next04Controller by inject<Next04Controller>()
    val next05Controller by inject<Next05Controller>()

    route("cerisier"){
        route("next01") {
            get {
                call.respond(next01Controller.getNext01StaList())
            }
        }
        route("next01") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val next01StaData = next01Controller.getNext01StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(next01StaData)
                }
            }
        }
        route("next02") {
            get {
                call.respond(next02Controller.getNext02StaList())
            }
        }
        route("next02") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val next02StaData = next02Controller.getNext02StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(next02StaData)
                }
            }
        }
        route("next03") {
            get {
                call.respond(next03Controller.getNext03StaList())
            }
        }
        route("next03") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val next03StaData = next03Controller.getNext03StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(next03StaData)
                }
            }
        }
        route("next04") {
            get {
                call.respond(next04Controller.getNext04StaList())
            }
        }
        route("next04") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val next04StaData = next04Controller.getNext04StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(next04StaData)
                }
            }
        }
        route("next05") {
            get {
                call.respond(next05Controller.getNext05StaList())
            }
        }
        route("next05") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val next05StaData = next05Controller.getNext05StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(next05StaData)
                }
            }
        }
    }
}
