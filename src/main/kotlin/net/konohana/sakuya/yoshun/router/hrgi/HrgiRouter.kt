package net.konohana.sakuya.yoshun.router.hrgi

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import kotlin.getValue
import net.konohana.sakuya.yoshun.controller.hrgi.Hrgi01Controller
import net.konohana.sakuya.yoshun.controller.hrgi.Hrgi02Controller

import org.koin.ktor.ext.inject

fun Route.hrgiRouter() {
    val hrgi01Controller by inject<Hrgi01Controller>()
    val hrgi02Controller by inject<Hrgi02Controller>()



    route("cerisier") {
        route("hrgi01") {
            get {
                call.respond(hrgi01Controller.getHrgi01StaList())
            }
        }
        route("hrgi01") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"] ?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val hrgi01StaData = hrgi01Controller.getHrgi01StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(hrgi01StaData)
                }
            }
        }
        route("hrgi02") {
            get {
                call.respond(hrgi02Controller.getHrgi02StaList())
            }
        }
        route("hrgi02") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"] ?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val hrgi02StaData = hrgi02Controller.getHrgi02StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(hrgi02StaData)
                }
            }
        }
    }
}
