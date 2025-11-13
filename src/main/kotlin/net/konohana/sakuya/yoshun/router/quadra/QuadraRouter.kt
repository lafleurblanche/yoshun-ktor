package net.konohana.sakuya.yoshun.router.quadra

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import kotlin.getValue
import net.konohana.sakuya.yoshun.controller.quadra.Quadra01Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra02Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra03Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra04Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra05Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra06Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra07Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra08Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra09Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra10Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra11Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra12Controller
import org.koin.ktor.ext.inject

fun Route.quadraRouter() {
    val quadra01Controller by inject<Quadra01Controller>()
    val quadra02Controller by inject<Quadra02Controller>()
    val quadra03Controller by inject<Quadra03Controller>()
    val quadra04Controller by inject<Quadra04Controller>()
    val quadra05Controller by inject<Quadra05Controller>()
    val quadra06Controller by inject<Quadra06Controller>()
    val quadra07Controller by inject<Quadra07Controller>()
    val quadra08Controller by inject<Quadra08Controller>()
    val quadra09Controller by inject<Quadra09Controller>()
    val quadra10Controller by inject<Quadra10Controller>()
    val quadra11Controller by inject<Quadra11Controller>()
    val quadra12Controller by inject<Quadra12Controller>()
    route("cerisier") {
        route("quadra01") {
            get {
                call.respond(quadra01Controller.getQuadra01StaList())
            }
            // 2. ⭐ 新規：フロントエンド用全件取得（新しいDTO） -> GET /cerisier/quadra01/frontend
            route("frontend") {
                get {
                    // 新しいコントローラメソッドを呼び出す
                    call.respond(quadra01Controller.getQuadra01FrontendList())
                }
            }
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra01StaData = quadra01Controller.getQuadra01StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra01StaData)
                }
            }
        }
        route("quadra02") {
            get {
                call.respond(quadra02Controller.getQuadra02StaList())
            }
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra02StaData = quadra02Controller.getQuadra02StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra02StaData)
                }
            }
        }
        route("quadra03") {
            get {
                call.respond(quadra03Controller.getQuadra03StaList())
            }
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra03StaData = quadra03Controller.getQuadra03StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra03StaData)
                }
            }
        }
        route("quadra04") {
            get {
                call.respond(quadra04Controller.getQuadra04StaList())
            }
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra04StaData = quadra04Controller.getQuadra04StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra04StaData)
                }
            }
        }
        route("quadra05") {
            get {
                call.respond(quadra05Controller.getQuadra05StaList())
            }
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra05StaData = quadra05Controller.getQuadra05StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra05StaData)
                }
            }
        }
        route("quadra06") {
            get {
                call.respond(quadra06Controller.getQuadra06StaList())
            }
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra06StaData = quadra06Controller.getQuadra06StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra06StaData)
                }
            }
        }
        route("quadra07") {
            get {
                call.respond(quadra07Controller.getQuadra07StaList())
            }
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra07StaData = quadra07Controller.getQuadra07StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra07StaData)
                }
            }
        }
        route("quadra08") {
            get {
                call.respond(quadra08Controller.getQuadra08StaList())
            }
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra08StaData = quadra08Controller.getQuadra08StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra08StaData)
                }
            }
        }
        route("quadra09") {
            get {
                call.respond(quadra09Controller.getQuadra09StaList())
            }
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra09StaData = quadra09Controller.getQuadra09StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra09StaData)
                }
            }
        }
        route("quadra10") {
            get {
                call.respond(quadra10Controller.getQuadra10StaList())
            }
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra10StaData = quadra10Controller.getQuadra10StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra10StaData)
                }
            }
        }
        route("quadra11") {
            get {
                call.respond(quadra11Controller.getQuadra11StaList())
            }
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra11StaData = quadra11Controller.getQuadra11StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra11StaData)
                }
            }
        }
        route("quadra12") {
            get {
                call.respond(quadra12Controller.getQuadra12StaList())
            }
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra12StaData = quadra12Controller.getQuadra12StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra12StaData)
                }
            }
        }
    }
}
