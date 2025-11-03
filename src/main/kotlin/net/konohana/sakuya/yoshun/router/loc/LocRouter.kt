package net.konohana.sakuya.yoshun.router.loc

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import kotlin.getValue
import net.konohana.sakuya.yoshun.controller.loc.Loc01Controller
import net.konohana.sakuya.yoshun.controller.loc.Loc02Controller
import net.konohana.sakuya.yoshun.controller.loc.Loc03Controller
import net.konohana.sakuya.yoshun.controller.loc.Loc04Controller
import net.konohana.sakuya.yoshun.controller.loc.Loc05Controller
import net.konohana.sakuya.yoshun.controller.loc.Loc06Controller
import net.konohana.sakuya.yoshun.controller.loc.Loc07Controller
import net.konohana.sakuya.yoshun.controller.loc.Loc08Controller
import net.konohana.sakuya.yoshun.controller.loc.Loc09Controller
import net.konohana.sakuya.yoshun.controller.loc.Loc10Controller
import net.konohana.sakuya.yoshun.controller.loc.Loc11Controller
import org.koin.ktor.ext.inject

fun Route.locRouter() {
    val loc01Controller by inject<Loc01Controller>()
    val loc02Controller by inject<Loc02Controller>()
    val loc03Controller by inject<Loc03Controller>()
    val loc04Controller by inject<Loc04Controller>()
    val loc05Controller by inject<Loc05Controller>()
    val loc06Controller by inject<Loc06Controller>()
    val loc07Controller by inject<Loc07Controller>()
    val loc08Controller by inject<Loc08Controller>()
    val loc09Controller by inject<Loc09Controller>()
    val loc10Controller by inject<Loc10Controller>()
    val loc11Controller by inject<Loc11Controller>()
    route("cerisier") {
        route("loc01") {
            get {
                call.respond(loc01Controller.getLoc01StaList())
            }
        }
        route("loc01") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"] ?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val loc01StaData = loc01Controller.getLoc01StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(loc01StaData)
                }
            }
        }
        route("loc02") {
            get {
                call.respond(loc02Controller.getLoc02StaList())
            }
        }
        route("loc02") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"] ?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val loc02StaData = loc02Controller.getLoc02StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(loc02StaData)
                }
            }
        }
        route("loc03") {
            get {
                call.respond(loc03Controller.getLoc03StaList())
            }
        }
        route("loc03") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"] ?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val loc03StaData = loc03Controller.getLoc03StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(loc03StaData)
                }
            }
        }
        route("loc04") {
            get {
                call.respond(loc04Controller.getLoc04StaList())
            }
        }
        route("loc04") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"] ?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val loc04StaData = loc04Controller.getLoc04StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(loc04StaData)
                }
            }
        }
        route("loc05") {
            get {
                call.respond(loc05Controller.getLoc05StaList())
            }
        }
        route("loc05") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"] ?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val loc05StaData = loc05Controller.getLoc05StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(loc05StaData)
                }
            }
        }
        route("loc06") {
            get {
                call.respond(loc06Controller.getLoc06StaList())
            }
        }
        route("loc06") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"] ?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val loc06StaData = loc06Controller.getLoc06StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(loc06StaData)
                }
            }
        }
        route("loc07") {
            get {
                call.respond(loc07Controller.getLoc07StaList())
            }
        }
        route("loc07") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"] ?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val loc07StaData = loc07Controller.getLoc07StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(loc07StaData)
                }
            }
        }
        route("loc08") {
            get {
                call.respond(loc08Controller.getLoc08StaList())
            }
        }
        route("loc08") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"] ?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val loc08StaData = loc08Controller.getLoc08StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(loc08StaData)
                }
            }
        }
        route("loc09") {
            get {
                call.respond(loc09Controller.getLoc09StaList())
            }
        }
        route("loc09") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"] ?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val loc09StaData = loc09Controller.getLoc09StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(loc09StaData)
                }
            }
        }
        route("loc10") {
            get {
                call.respond(loc10Controller.getLoc10StaList())
            }
        }
        route("loc10") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"] ?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val loc10StaData = loc10Controller.getLoc10StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(loc10StaData)
                }
            }
        }
        route("loc11") {
            get {
                call.respond(loc11Controller.getLoc11StaList())
            }
        }
        route("loc11") {
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"] ?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val loc11StaData = loc11Controller.getLoc11StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(loc11StaData)
                }
            }
        }
    }
}
