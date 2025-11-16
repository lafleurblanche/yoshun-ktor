package net.konohana.sakuya.yoshun.dimodules

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
import net.konohana.sakuya.yoshun.services.quadra.Quadra01Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra02Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra03Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra04Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra05Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra06Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra07Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra08Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra09Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra10Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra11Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra12Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra13Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra14Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra15Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra16Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra17Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra18Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra19Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra20Service
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object QuadraModules {
    val quadraKoinModules = module {

        // services
        singleOf(::Quadra01Service)
        singleOf(::Quadra02Service)
        singleOf(::Quadra03Service)
        singleOf(::Quadra04Service)
        singleOf(::Quadra05Service)
        singleOf(::Quadra06Service)
        singleOf(::Quadra07Service)
        singleOf(::Quadra08Service)
        singleOf(::Quadra09Service)
        singleOf(::Quadra10Service)
        singleOf(::Quadra11Service)
        singleOf(::Quadra12Service)
        singleOf(::Quadra13Service)
        singleOf(::Quadra14Service)
        singleOf(::Quadra15Service)
        singleOf(::Quadra16Service)
        singleOf(::Quadra17Service)
        singleOf(::Quadra18Service)
        singleOf(::Quadra19Service)
        singleOf(::Quadra20Service)

        // controllers
        singleOf(::Quadra01Controller)
        singleOf(::Quadra02Controller)
        singleOf(::Quadra03Controller)
        singleOf(::Quadra04Controller)
        singleOf(::Quadra05Controller)
        singleOf(::Quadra06Controller)
        singleOf(::Quadra07Controller)
        singleOf(::Quadra08Controller)
        singleOf(::Quadra09Controller)
        singleOf(::Quadra10Controller)
        singleOf(::Quadra11Controller)
        singleOf(::Quadra12Controller)
        singleOf(::Quadra13Controller)
        singleOf(::Quadra14Controller)
        singleOf(::Quadra15Controller)
        singleOf(::Quadra16Controller)
        singleOf(::Quadra17Controller)
        singleOf(::Quadra18Controller)
        singleOf(::Quadra19Controller)
        singleOf(::Quadra20Controller)
    }
}
