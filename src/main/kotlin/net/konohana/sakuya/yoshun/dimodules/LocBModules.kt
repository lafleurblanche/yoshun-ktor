package net.konohana.sakuya.yoshun.dimodules

import net.konohana.sakuya.yoshun.controller.locb.LocB01Controller
import net.konohana.sakuya.yoshun.controller.locb.LocB02Controller
import net.konohana.sakuya.yoshun.controller.locb.LocB03Controller
import net.konohana.sakuya.yoshun.controller.locb.LocB04Controller
import net.konohana.sakuya.yoshun.services.locb.LocB01Service
import net.konohana.sakuya.yoshun.services.locb.LocB02Service
import net.konohana.sakuya.yoshun.services.locb.LocB03Service
import net.konohana.sakuya.yoshun.services.locb.LocB04Service
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object LocBModules {
    val locBKoinModules = module {

        // service
        singleOf(::LocB01Service)
        singleOf(::LocB02Service)
        singleOf(::LocB03Service)
        singleOf(::LocB04Service)

        // controller
        singleOf(::LocB01Controller)
        singleOf(::LocB02Controller)
        singleOf(::LocB03Controller)
        singleOf(::LocB04Controller)
    }
}
