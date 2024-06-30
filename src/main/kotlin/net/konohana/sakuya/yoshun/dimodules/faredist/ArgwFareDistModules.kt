package net.konohana.sakuya.yoshun.dimodules.faredist

import net.konohana.sakuya.yoshun.services.faredist.argw.Argw01FareDistService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object ArgwFareDistModules {
    val argwFareDistKoinModules = module {

        // service
        singleOf(::Argw01FareDistService)

    }
}
