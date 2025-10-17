package net.konohana.sakuya.yoshun.dimodules

import net.konohana.sakuya.yoshun.controller.hrgi.Hrgi01Controller
import net.konohana.sakuya.yoshun.controller.hrgi.Hrgi02Controller
import net.konohana.sakuya.yoshun.services.hrgi.Hrgi01Service
import net.konohana.sakuya.yoshun.services.hrgi.Hrgi02Service
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object HrgiModules {
    val hrgiKoinModules = module {

        // service
        singleOf(::Hrgi01Service)
        singleOf(::Hrgi02Service)

        // controller
        singleOf(::Hrgi01Controller)
        singleOf(::Hrgi02Controller)
    }
}
