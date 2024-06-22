package net.konohana.sakuya.yoshun.router.neue

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import net.konohana.sakuya.yoshun.controller.neue.Neue01Controller
import net.konohana.sakuya.yoshun.controller.neue.Neue02Controller
import net.konohana.sakuya.yoshun.controller.neue.Neue03Controller
import net.konohana.sakuya.yoshun.controller.neue.Neue04Controller
import net.konohana.sakuya.yoshun.controller.neue.Neue05Controller
import net.konohana.sakuya.yoshun.controller.neue.Neue06Controller
import net.konohana.sakuya.yoshun.controller.neue.Neue07Controller
import net.konohana.sakuya.yoshun.controller.neue.Neue08Controller
import org.koin.ktor.ext.inject

fun Route.neueRouter() {
    val neue01Controller by inject<Neue01Controller>()
    val neue02Controller by inject<Neue02Controller>()
    val neue03Controller by inject<Neue03Controller>()
    val neue04Controller by inject<Neue04Controller>()
    val neue05Controller by inject<Neue05Controller>()
    val neue06Controller by inject<Neue06Controller>()
    val neue07Controller by inject<Neue07Controller>()
    val neue08Controller by inject<Neue08Controller>()
    route("neue01") {
        get {
            call.respond(neue01Controller.getNeue01StaList())
        }
    }
    route("neue01") {
        route("{staCode}") {
            get {
                val staCode = call.parameters["staCode"]?: run {
                    return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                }
                val neue01StaData = neue01Controller.getNeue01StaListByStaCode(staCode = staCode) ?: run {
                    return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                }
                call.respond(neue01StaData)
            }
        }
    }
    route("neue02") {
        get {
            call.respond(neue02Controller.getNeue02StaList())
        }
    }
    route("neue02") {
        route("{staCode}") {
            get {
                val staCode = call.parameters["staCode"]?: run {
                    return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                }
                val neue02StaData = neue02Controller.getNeue02StaListByStaCode(staCode = staCode) ?: run {
                    return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                }
                call.respond(neue02StaData)
            }
        }
    }
    route("neue03") {
        get {
            call.respond(neue03Controller.getNeue03StaList())
        }
    }
    route("neue03") {
        route("{staCode}") {
            get {
                val staCode = call.parameters["staCode"]?: run {
                    return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                }
                val neue03StaData = neue03Controller.getNeue03StaListByStaCode(staCode = staCode) ?: run {
                    return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                }
                call.respond(neue03StaData)
            }
        }
    }
    route("neue04") {
        get {
            call.respond(neue04Controller.getNeue04StaList())
        }
    }
    route("neue04") {
        route("{staCode}") {
            get {
                val staCode = call.parameters["staCode"]?: run {
                    return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                }
                val neue04StaData = neue04Controller.getNeue04StaListByStaCode(staCode = staCode) ?: run {
                    return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                }
                call.respond(neue04StaData)
            }
        }
    }
    route("neue05") {
        get {
            call.respond(neue05Controller.getNeue05StaList())
        }
    }
    route("neue05") {
        route("{staCode}") {
            get {
                val staCode = call.parameters["staCode"]?: run {
                    return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                }
                val neue05StaData = neue05Controller.getNeue05StaListByStaCode(staCode = staCode) ?: run {
                    return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                }
                call.respond(neue05StaData)
            }
        }
    }
    route("neue06") {
        get {
            call.respond(neue06Controller.getNeue06StaList())
        }
    }
    route("neue06") {
        route("{staCode}") {
            get {
                val staCode = call.parameters["staCode"]?: run {
                    return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                }
                val neue06StaData = neue06Controller.getNeue06StaListByStaCode(staCode = staCode) ?: run {
                    return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                }
                call.respond(neue06StaData)
            }
        }
    }
    route("neue07") {
        get {
            call.respond(neue07Controller.getNeue07StaList())
        }
    }
    route("neue07") {
        route("{staCode}") {
            get {
                val staCode = call.parameters["staCode"]?: run {
                    return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                }
                val neue07StaData = neue07Controller.getNeue07StaListByStaCode(staCode = staCode) ?: run {
                    return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                }
                call.respond(neue07StaData)
            }
        }
    }
    route("neue08") {
        get {
            call.respond(neue08Controller.getNeue08StaList())
        }
    }
    route("neue08") {
        route("{staCode}") {
            get {
                val staCode = call.parameters["staCode"]?: run {
                    return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                }
                val neue08StaData = neue08Controller.getNeue08StaListByStaCode(staCode = staCode) ?: run {
                    return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                }
                call.respond(neue08StaData)
            }
        }
    }
}
