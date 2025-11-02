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

    }
}
