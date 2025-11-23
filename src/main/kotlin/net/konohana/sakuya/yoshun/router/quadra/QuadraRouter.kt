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
import net.konohana.sakuya.yoshun.controller.quadra.Quadra13Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra14Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra15Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra16Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra17Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra18Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra19Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra20Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra21Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra22Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra23Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra24Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra25Controller
import net.konohana.sakuya.yoshun.controller.quadra.Quadra26Controller
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
    val quadra13Controller by inject<Quadra13Controller>()
    val quadra14Controller by inject<Quadra14Controller>()
    val quadra15Controller by inject<Quadra15Controller>()
    val quadra16Controller by inject<Quadra16Controller>()
    val quadra17Controller by inject<Quadra17Controller>()
    val quadra18Controller by inject<Quadra18Controller>()
    val quadra19Controller by inject<Quadra19Controller>()
    val quadra20Controller by inject<Quadra20Controller>()
    val quadra21Controller by inject<Quadra21Controller>()
    val quadra22Controller by inject<Quadra22Controller>()
    val quadra23Controller by inject<Quadra23Controller>()
    val quadra24Controller by inject<Quadra24Controller>()
    val quadra25Controller by inject<Quadra25Controller>()
    val quadra26Controller by inject<Quadra26Controller>()

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
            route("frontend") {
                get {
                    // 新しいコントローラメソッドを呼び出す
                    call.respond(quadra02Controller.getQuadra02FrontendList())
                }
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
            route("frontend") {
                get {
                    // 新しいコントローラメソッドを呼び出す
                    call.respond(quadra03Controller.getQuadra03FrontendList())
                }
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
            route("frontend") {
                get {
                    // 新しいコントローラメソッドを呼び出す
                    call.respond(quadra04Controller.getQuadra04FrontendList())
                }
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
            route("frontend") {
                get {
                    // 新しいコントローラメソッドを呼び出す
                    call.respond(quadra05Controller.getQuadra05FrontendList())
                }
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
            route("frontend") {
                get {
                    // 新しいコントローラメソッドを呼び出す
                    call.respond(quadra06Controller.getQuadra06FrontendList())
                }
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
            route("frontend") {
                get {
                    // 新しいコントローラメソッドを呼び出す
                    call.respond(quadra07Controller.getQuadra07FrontendList())
                }
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
            route("frontend") {
                get {
                    // 新しいコントローラメソッドを呼び出す
                    call.respond(quadra08Controller.getQuadra08FrontendList())
                }
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
            route("frontend") {
                get {
                    // 新しいコントローラメソッドを呼び出す
                    call.respond(quadra09Controller.getQuadra09FrontendList())
                }
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
            route("frontend") {
                get {
                    // 新しいコントローラメソッドを呼び出す
                    call.respond(quadra10Controller.getQuadra10FrontendList())
                }
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
            route("frontend") {
                get {
                    // 新しいコントローラメソッドを呼び出す
                    call.respond(quadra11Controller.getQuadra11FrontendList())
                }
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
            route("frontend") {
                get {
                    // 新しいコントローラメソッドを呼び出す
                    call.respond(quadra12Controller.getQuadra12FrontendList())
                }
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
        route("quadra13") {
            get {
                call.respond(quadra13Controller.getQuadra13StaList())
            }
            route("frontend") {
                get {
                    // 新しいコントローラメソッドを呼び出す
                    call.respond(quadra13Controller.getQuadra13FrontendList())
                }
            }
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra13StaData = quadra13Controller.getQuadra13StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra13StaData)
                }
            }
        }
        route("quadra14") {
            get {
                call.respond(quadra14Controller.getQuadra14StaList())
            }
            route("frontend") {
                get {
                    // 新しいコントローラメソッドを呼び出す
                    call.respond(quadra14Controller.getQuadra14FrontendList())
                }
            }
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra14StaData = quadra14Controller.getQuadra14StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra14StaData)
                }
            }
        }
        route("quadra15") {
            get {
                call.respond(quadra15Controller.getQuadra15StaList())
            }
            route("frontend") {
                get {
                    // 新しいコントローラメソッドを呼び出す
                    call.respond(quadra15Controller.getQuadra15FrontendList())
                }
            }
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra15StaData = quadra15Controller.getQuadra15StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra15StaData)
                }
            }
        }
        route("quadra16") {
            get {
                call.respond(quadra16Controller.getQuadra16StaList())
            }
            route("frontend") {
                get {
                    // 新しいコントローラメソッドを呼び出す
                    call.respond(quadra16Controller.getQuadra16FrontendList())
                }
            }
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra16StaData = quadra16Controller.getQuadra16StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra16StaData)
                }
            }
        }
        route("quadra17") {
            get {
                call.respond(quadra17Controller.getQuadra17StaList())
            }
            route("frontend") {
                get {
                    // 新しいコントローラメソッドを呼び出す
                    call.respond(quadra17Controller.getQuadra17FrontendList())
                }
            }
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra17StaData = quadra17Controller.getQuadra17StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra17StaData)
                }
            }
        }
        route("quadra18") {
            get {
                call.respond(quadra18Controller.getQuadra18StaList())
            }
            route("frontend") {
                get {
                    // 新しいコントローラメソッドを呼び出す
                    call.respond(quadra18Controller.getQuadra18FrontendList())
                }
            }
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra18StaData = quadra18Controller.getQuadra18StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra18StaData)
                }
            }
        }
        route("quadra19") {
            get {
                call.respond(quadra19Controller.getQuadra19StaList())
            }
            // 2. ⭐ 新規：フロントエンド用全件取得（新しいDTO） -> GET /cerisier/quadra19/frontend
            route("frontend") {
                get {
                    // 新しいコントローラメソッドを呼び出す
                    call.respond(quadra19Controller.getQuadra19FrontendList())
                }
            }
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra19StaData = quadra19Controller.getQuadra19StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra19StaData)
                }
            }
        }
        route("quadra20") {
            get {
                call.respond(quadra20Controller.getQuadra20StaList())
            }
            route("frontend") {
                get {
                    // 新しいコントローラメソッドを呼び出す
                    call.respond(quadra20Controller.getQuadra20FrontendList())
                }
            }
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra20StaData = quadra20Controller.getQuadra20StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra20StaData)
                }
            }
        }
        route("quadra21") {
            get {
                call.respond(quadra21Controller.getQuadra21StaList())
            }
            route("frontend") {
                get {
                    // 新しいコントローラメソッドを呼び出す
                    call.respond(quadra21Controller.getQuadra21FrontendList())
                }
            }
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra21StaData = quadra21Controller.getQuadra21StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra21StaData)
                }
            }
        }
        route("quadra22") {
            get {
                call.respond(quadra22Controller.getQuadra22StaList())
            }
            route("frontend") {
                get {
                    // 新しいコントローラメソッドを呼び出す
                    call.respond(quadra22Controller.getQuadra22FrontendList())
                }
            }
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra22StaData = quadra22Controller.getQuadra22StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra22StaData)
                }
            }
        }
        route("quadra23") {
            get {
                call.respond(quadra23Controller.getQuadra23StaList())
            }
            route("frontend") {
                get {
                    // 新しいコントローラメソッドを呼び出す
                    call.respond(quadra23Controller.getQuadra23FrontendList())
                }
            }
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra23StaData = quadra23Controller.getQuadra23StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra23StaData)
                }
            }
        }
        route("quadra24") {
            get {
                call.respond(quadra24Controller.getQuadra24StaList())
            }
            // 2. ⭐ 新規：フロントエンド用全件取得（新しいDTO） -> GET /cerisier/quadra24/frontend
            route("frontend") {
                get {
                    // 新しいコントローラメソッドを呼び出す
                    call.respond(quadra24Controller.getQuadra24FrontendList())
                }
            }
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra24StaData = quadra24Controller.getQuadra24StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra24StaData)
                }
            }
        }
        route("quadra25") {
            get {
                call.respond(quadra25Controller.getQuadra25StaList())
            }
            route("frontend") {
                get {
                    // 新しいコントローラメソッドを呼び出す
                    call.respond(quadra25Controller.getQuadra25FrontendList())
                }
            }
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra25StaData = quadra25Controller.getQuadra25StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra25StaData)
                }
            }
        }
        route("quadra26") {
            get {
                call.respond(quadra26Controller.getQuadra26StaList())
            }
            route("frontend") {
                get {
                    // 新しいコントローラメソッドを呼び出す
                    call.respond(quadra26Controller.getQuadra26FrontendList())
                }
            }
            route("{staCode}") {
                get {
                    val staCode = call.parameters["staCode"]?: run {
                        return@get call.respond(HttpStatusCode.BadRequest, "staCodeが指定されていません")
                    }
                    val quadra26StaData = quadra26Controller.getQuadra26StaListByStaCode(staCode = staCode) ?: run {
                        return@get call.respond(HttpStatusCode.NotFound, "データが存在しません 駅名コード: $staCode")
                    }
                    call.respond(quadra26StaData)
                }
            }
        }
    }
}
