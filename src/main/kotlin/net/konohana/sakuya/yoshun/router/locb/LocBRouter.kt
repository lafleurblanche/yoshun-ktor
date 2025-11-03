package net.konohana.sakuya.yoshun.router.locb

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import net.konohana.sakuya.yoshun.controller.locb.LocB01Controller
import net.konohana.sakuya.yoshun.controller.locb.LocB02Controller
import net.konohana.sakuya.yoshun.controller.locb.LocB03Controller
import net.konohana.sakuya.yoshun.controller.locb.LocB04Controller
import org.koin.ktor.ext.inject

fun Route.locbRouter() {
    val locb01Controller by inject<LocB01Controller>()
    val locb02Controller by inject<LocB02Controller>()
    val locb03Controller by inject<LocB03Controller>()
    val locb04Controller by inject<LocB04Controller>()
    route("cerisier") {
        route("locb01") {
            get {
                call.respond(locb01Controller.getLocB01StaList())
            }
        }
        route("locb01") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"] ?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val locB01StaData = locb01Controller.getLocB01StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(locB01StaData)
                }
            }
        }
        route("locb02") {
            get {
                call.respond(locb02Controller.getLocB02StaList())
            }
        }
        route("locb02") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"] ?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val locB02StaData = locb02Controller.getLocB02StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(locB02StaData)
                }
            }
        }
        route("locb03") {
            get {
                call.respond(locb03Controller.getLocB03StaList())
            }
        }
        route("locb03") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"] ?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val locB03StaData = locb03Controller.getLocB03StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(locB03StaData)
                }
            }
        }
        route("locb04") {
            get {
                call.respond(locb04Controller.getLocB04StaList())
            }
        }
        route("locb04") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"] ?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val locB04StaData = locb04Controller.getLocB04StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(locB04StaData)
                }
            }
        }
    }
}
