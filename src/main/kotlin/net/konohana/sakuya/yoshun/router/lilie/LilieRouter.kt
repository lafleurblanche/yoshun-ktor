package net.konohana.sakuya.yoshun.router.lilie

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import net.konohana.sakuya.yoshun.controller.lilie.Lilie01Controller
import net.konohana.sakuya.yoshun.controller.lilie.Lilie02Controller
import net.konohana.sakuya.yoshun.controller.lilie.Lilie03Controller
import net.konohana.sakuya.yoshun.controller.lilie.Lilie04Controller
import net.konohana.sakuya.yoshun.controller.lilie.Lilie05Controller
import net.konohana.sakuya.yoshun.controller.lilie.Lilie06Controller
import net.konohana.sakuya.yoshun.controller.lilie.Lilie07Controller
import org.koin.ktor.ext.inject

fun Route.lilieRouter() {
    val lilie01Controller by inject<Lilie01Controller>()
    val lilie02Controller by inject<Lilie02Controller>()
    val lilie03Controller by inject<Lilie03Controller>()
    val lilie04Controller by inject<Lilie04Controller>()
    val lilie05Controller by inject<Lilie05Controller>()
    val lilie06Controller by inject<Lilie06Controller>()
    val lilie07Controller by inject<Lilie07Controller>()

    route("cerisier") {
        route("lilie01") {
            get {
                call.respond(lilie01Controller.getLilie01StaList())
            }
        }
        route("lilie01") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val lilie01StaData = lilie01Controller.getLilie01StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(lilie01StaData)
                }
            }
        }
        route("lilie02") {
            get {
                call.respond(lilie02Controller.getLilie02StaList())
            }
        }
        route("lilie02") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val lilie02StaData = lilie02Controller.getLilie02StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(lilie02StaData)
                }
            }
        }
        route("lilie03") {
            get {
                call.respond(lilie03Controller.getLilie03StaList())
            }
        }
        route("lilie03") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val lilie03StaData = lilie03Controller.getLilie03StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(lilie03StaData)
                }
            }
        }
        route("lilie04") {
            get {
                call.respond(lilie04Controller.getLilie04StaList())
            }
        }
        route("lilie04") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val lilie04StaData = lilie04Controller.getLilie04StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(lilie04StaData)
                }
            }
        }
        route("lilie05") {
            get {
                call.respond(lilie05Controller.getLilie05StaList())
            }
        }
        route("lilie05") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val lilie05StaData = lilie05Controller.getLilie05StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(lilie05StaData)
                }
            }
        }
        route("lilie06") {
            get {
                call.respond(lilie06Controller.getLilie06StaList())
            }
        }
        route("lilie06") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val lilie06StaData = lilie06Controller.getLilie06StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(lilie06StaData)
                }
            }
        }
        route("lilie07") {
            get {
                call.respond(lilie07Controller.getLilie07StaList())
            }
        }
        route("lilie07") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val lilie07StaData = lilie07Controller.getLilie07StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(lilie07StaData)
                }
            }
        }
    }
}
