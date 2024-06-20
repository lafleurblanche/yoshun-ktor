package net.konohana.sakuya.yoshun.dimodules

import net.konohana.sakuya.yoshun.controller.enju.Enju01Controller
import net.konohana.sakuya.yoshun.controller.enju.Enju02Controller
import net.konohana.sakuya.yoshun.controller.enju.Enju03Controller
import net.konohana.sakuya.yoshun.services.enju.Enju01Service
import net.konohana.sakuya.yoshun.services.enju.Enju02Service
import net.konohana.sakuya.yoshun.services.enju.Enju03Service
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object EnjuModules {
    val enjuKoinModules = module {

        // service
        singleOf(::Enju01Service)
        singleOf(::Enju02Service)
        singleOf(::Enju03Service)

        // controller
        singleOf(::Enju01Controller)
        singleOf(::Enju02Controller)
        singleOf(::Enju03Controller)
    }
}
