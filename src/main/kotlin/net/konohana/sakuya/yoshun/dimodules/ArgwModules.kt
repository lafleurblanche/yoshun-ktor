package net.konohana.sakuya.yoshun.dimodules

import net.konohana.sakuya.yoshun.controller.argw.Argw01Controller
import net.konohana.sakuya.yoshun.services.argw.Argw01Service
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object ArgwModules {
    val argwKoinModules = module {

        // service
        singleOf(::Argw01Service)

        // controller
        singleOf(::Argw01Controller)

    }
}
