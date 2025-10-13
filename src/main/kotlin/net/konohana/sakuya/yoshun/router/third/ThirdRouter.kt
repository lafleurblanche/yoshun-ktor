package net.konohana.sakuya.yoshun.router.third

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import kotlin.getValue
import net.konohana.sakuya.yoshun.controller.third.Third01Controller
import net.konohana.sakuya.yoshun.controller.third.Third02Controller
import net.konohana.sakuya.yoshun.controller.third.Third03Controller
import net.konohana.sakuya.yoshun.controller.third.Third04Controller
import net.konohana.sakuya.yoshun.controller.third.Third05Controller
import net.konohana.sakuya.yoshun.controller.third.Third06Controller
import net.konohana.sakuya.yoshun.controller.third.Third07Controller
import net.konohana.sakuya.yoshun.controller.third.Third08Controller
import org.koin.ktor.ext.inject

fun Route.thirdRouter() {
    val third01Controller by inject<Third01Controller>()
    val third02Controller by inject<Third02Controller>()
    val third03Controller by inject<Third03Controller>()
    val third04Controller by inject<Third04Controller>()
    val third05Controller by inject<Third05Controller>()
    val third06Controller by inject<Third06Controller>()
    val third07Controller by inject<Third07Controller>()
    val third08Controller by inject<Third08Controller>()

    route("third01") {
        get {
            call.respond(third01Controller.getThird01StaList())
        }
    }
    route("third01") {
        route("{staCode}") {
            get {
                val staCode = call.parameters["staCode"]?: run {
                    return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                }
                val third01StaData = third01Controller.getThird01StaListByStaCode(staCode = staCode) ?: run {
                    return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                }
                call.respond(third01StaData)
            }
        }
    }
    route("third02") {
        get {
            call.respond(third02Controller.getThird02StaList())
        }
    }
    route("third02") {
        route("{staCode}") {
            get {
                val staCode = call.parameters["staCode"]?: run {
                    return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                }
                val third02StaData = third02Controller.getThird02StaListByStaCode(staCode = staCode) ?: run {
                    return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                }
                call.respond(third02StaData)
            }
        }
    }
    route("third03") {
        get {
            call.respond(third03Controller.getThird03StaList())
        }
    }
    route("third03") {
        route("{staCode}") {
            get {
                val staCode = call.parameters["staCode"]?: run {
                    return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                }
                val third03StaData = third03Controller.getThird03StaListByStaCode(staCode = staCode) ?: run {
                    return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                }
                call.respond(third03StaData)
            }
        }
    }
    route("third04") {
        get {
            call.respond(third04Controller.getThird04StaList())
        }
    }
    route("third04") {
        route("{staCode}") {
            get {
                val staCode = call.parameters["staCode"]?: run {
                    return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                }
                val third04StaData = third04Controller.getThird04StaListByStaCode(staCode = staCode) ?: run {
                    return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                }
                call.respond(third04StaData)
            }
        }
    }
    route("third05") {
        get {
            call.respond(third05Controller.getThird05StaList())
        }
    }
    route("third05") {
        route("{staCode}") {
            get {
                val staCode = call.parameters["staCode"]?: run {
                    return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                }
                val third05StaData = third05Controller.getThird05StaListByStaCode(staCode = staCode) ?: run {
                    return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                }
                call.respond(third05StaData)
            }
        }
    }
    route("third06") {
        get {
            call.respond(third06Controller.getThird06StaList())
        }
    }
    route("third06") {
        route("{staCode}") {
            get {
                val staCode = call.parameters["staCode"]?: run {
                    return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                }
                val third06StaData = third06Controller.getThird06StaListByStaCode(staCode = staCode) ?: run {
                    return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                }
                call.respond(third06StaData)
            }
        }
    }
    route("third07") {
        get {
            call.respond(third07Controller.getThird07StaList())
        }
    }
    route("third07") {
        route("{staCode}") {
            get {
                val staCode = call.parameters["staCode"]?: run {
                    return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                }
                val third07StaData = third07Controller.getThird07StaListByStaCode(staCode = staCode) ?: run {
                    return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                }
                call.respond(third07StaData)
            }
        }
    }
    route("third08") {
        get {
            call.respond(third08Controller.getThird08StaList())
        }
    }
    route("third08") {
        route("{staCode}") {
            get {
                val staCode = call.parameters["staCode"]?: run {
                    return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                }
                val third08StaData = third08Controller.getThird08StaListByStaCode(staCode = staCode) ?: run {
                    return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                }
                call.respond(third08StaData)
            }
        }
    }
}
