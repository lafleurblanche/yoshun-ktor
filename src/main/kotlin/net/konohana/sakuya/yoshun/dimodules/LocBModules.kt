package net.konohana.sakuya.yoshun.dimodules

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
    }
}
