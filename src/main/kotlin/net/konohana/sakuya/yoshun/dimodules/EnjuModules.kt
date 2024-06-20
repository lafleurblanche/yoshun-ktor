package net.konohana.sakuya.yoshun.dimodules

import net.konohana.sakuya.yoshun.controller.enju.Enju01Controller
import net.konohana.sakuya.yoshun.services.enju.Enju01Service
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object EnjuModules {
    val enjuKoinModules = module {

        // service
        singleOf(::Enju01Service)

        // controller
        singleOf(::Enju01Controller)
    }
}
