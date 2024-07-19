package net.konohana.sakuya.yoshun.dimodules.faredist

import net.konohana.sakuya.yoshun.controller.faredist.neue.Neue01FareDistController
import net.konohana.sakuya.yoshun.controller.faredist.neue.Neue02FareDistController
import net.konohana.sakuya.yoshun.controller.faredist.neue.Neue03FareDistController
import net.konohana.sakuya.yoshun.controller.faredist.neue.Neue04FareDistController
import net.konohana.sakuya.yoshun.services.faredist.neue.Neue01FareDistService
import net.konohana.sakuya.yoshun.services.faredist.neue.Neue02FareDistService
import net.konohana.sakuya.yoshun.services.faredist.neue.Neue03FareDistService
import net.konohana.sakuya.yoshun.services.faredist.neue.Neue04FareDistService
import net.konohana.sakuya.yoshun.services.faredist.neue.Neue05FareDistService
import net.konohana.sakuya.yoshun.services.faredist.neue.Neue06FareDistService
import net.konohana.sakuya.yoshun.services.faredist.neue.Neue07FareDistService
import net.konohana.sakuya.yoshun.services.faredist.neue.Neue08FareDistService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object NeueFareDistModules {
    val neueFareDistKoinModules = module {

        // service
        singleOf(::Neue01FareDistService)
        singleOf(::Neue02FareDistService)
        singleOf(::Neue03FareDistService)
        singleOf(::Neue04FareDistService)
        singleOf(::Neue05FareDistService)
        singleOf(::Neue06FareDistService)
        singleOf(::Neue07FareDistService)
        singleOf(::Neue08FareDistService)

        // controller
        singleOf(::Neue01FareDistController)
        singleOf(::Neue02FareDistController)
        singleOf(::Neue03FareDistController)
        singleOf(::Neue04FareDistController)
    }
}
