package net.konohana.sakuya.yoshun.router.enju

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import net.konohana.sakuya.yoshun.controller.enju.Enju01Controller
import net.konohana.sakuya.yoshun.controller.enju.Enju02Controller
import net.konohana.sakuya.yoshun.controller.enju.Enju03Controller
import net.konohana.sakuya.yoshun.controller.enju.Enju04Controller
import net.konohana.sakuya.yoshun.controller.enju.Enju05Controller
import net.konohana.sakuya.yoshun.controller.enju.Enju06Controller
import net.konohana.sakuya.yoshun.controller.enju.Enju07Controller
import net.konohana.sakuya.yoshun.controller.enju.Enju08Controller
import net.konohana.sakuya.yoshun.controller.enju.Enju09Controller
import net.konohana.sakuya.yoshun.controller.enju.Enju10Controller
import net.konohana.sakuya.yoshun.controller.enju.Enju11Controller
import org.koin.ktor.ext.inject

fun Route.enjuRouter() {
    val enju01Controller by inject<Enju01Controller>()
    val enju02Controller by inject<Enju02Controller>()
    val enju03Controller by inject<Enju03Controller>()
    val enju04Controller by inject<Enju04Controller>()
    val enju05Controller by inject<Enju05Controller>()
    val enju06Controller by inject<Enju06Controller>()
    val enju07Controller by inject<Enju07Controller>()
    val enju08Controller by inject<Enju08Controller>()
    val enju09Controller by inject<Enju09Controller>()
    val enju10Controller by inject<Enju10Controller>()
    val enju11Controller by inject<Enju11Controller>()
    route("cerisier") {
        route("enju01") {
            get {
                call.respond(enju01Controller.getEnju01StaList())
            }
        }
        route("enju01") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val enju01StaData = enju01Controller.getEnju01StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(enju01StaData)
                }
            }
        }
        route("enju02") {
            get {
                call.respond(enju02Controller.getEnju02StaList())
            }
        }
        route("enju02") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val enju02StaData = enju02Controller.getEnju02StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(enju02StaData)
                }
            }
        }
        route("enju03") {
            get {
                call.respond(enju03Controller.getEnju03StaList())
            }
        }
        route("enju03") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val enju03StaData = enju03Controller.getEnju03StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(enju03StaData)
                }
            }
        }
        route("enju04") {
            get {
                call.respond(enju04Controller.getEnju04StaList())
            }
        }
        route("enju04") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val enju04StaData = enju04Controller.getEnju04StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(enju04StaData)
                }
            }
        }
        route("enju05") {
            get {
                call.respond(enju05Controller.getEnju05StaList())
            }
        }
        route("enju05") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val enju05StaData = enju05Controller.getEnju05StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(enju05StaData)
                }
            }
        }
        route("enju06") {
            get {
                call.respond(enju06Controller.getEnju06StaList())
            }
        }
        route("enju06") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val enju06StaData = enju06Controller.getEnju06StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(enju06StaData)
                }
            }
        }
        route("enju07") {
            get {
                call.respond(enju07Controller.getEnju07StaList())
            }
        }
        route("enju07") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val enju07StaData = enju07Controller.getEnju07StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(enju07StaData)
                }
            }
        }
        route("enju08") {
            get {
                call.respond(enju08Controller.getEnju08StaList())
            }
        }
        route("enju08") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val enju08StaData = enju08Controller.getEnju08StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(enju08StaData)
                }
            }
        }
        route("enju09") {
            get {
                call.respond(enju09Controller.getEnju09StaList())
            }
        }
        route("enju09") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val enju09StaData = enju09Controller.getEnju09StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(enju09StaData)
                }
            }
        }
        route("enju10") {
            get {
                call.respond(enju10Controller.getEnju10StaList())
            }
        }
        route("enju10") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val enju10StaData = enju10Controller.getEnju10StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(enju10StaData)
                }
            }
        }
        route("enju11") {
            get {
                call.respond(enju11Controller.getEnju11StaList())
            }
        }
        route("enju11") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val enju11StaData = enju11Controller.getEnju11StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(enju11StaData)
                }
            }
        }
    }
}
