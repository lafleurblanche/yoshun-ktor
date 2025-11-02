package net.konohana.sakuya.yoshun.router.hnki

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import kotlin.getValue
import net.konohana.sakuya.yoshun.controller.hnki.Hnki01Controller
import net.konohana.sakuya.yoshun.controller.hnki.Hnki02Controller
import net.konohana.sakuya.yoshun.controller.hnki.Hnki03Controller
import net.konohana.sakuya.yoshun.controller.hnki.Hnki04Controller
import org.koin.ktor.ext.inject

fun Route.hnkiRouter() {
    val hnki01Controller by inject<Hnki01Controller>()
    val hnki02Controller by inject<Hnki02Controller>()
    val hnki03Controller by inject<Hnki03Controller>()
    val hnki04Controller by inject<Hnki04Controller>()


    route("cerisier") {
        route("hnki01") {
            get {
                call.respond(hnki01Controller.getHnki01StaList())
            }
        }
        route("hnki01") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"] ?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val hnki01StaData = hnki01Controller.getHnki01StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(hnki01StaData)
                }
            }
        }
        route("hnki02") {
            get {
                call.respond(hnki02Controller.getHnki02StaList())
            }
        }
        route("hnki02") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"] ?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val hnki02StaData = hnki02Controller.getHnki02StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(hnki02StaData)
                }
            }
        }
        route("hnki03") {
            get {
                call.respond(hnki03Controller.getHnki03StaList())
            }
        }
        route("hnki03") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"] ?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val hnki03StaData = hnki03Controller.getHnki03StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(hnki03StaData)
                }
            }
        }
        route("hnki04") {
            get {
                call.respond(hnki04Controller.getHnki04StaList())
            }
        }
        route("hnki04") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"] ?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val hnki04StaData = hnki04Controller.getHnki04StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(hnki04StaData)
                }
            }
        }
    }
}
