package net.konohana.sakuya.yoshun.dimodules.faredist

import net.konohana.sakuya.yoshun.controller.faredist.hrgi.Hrgi01FareDistController
import net.konohana.sakuya.yoshun.services.faredist.hrgi.Hrgi01FareDistService
import net.konohana.sakuya.yoshun.services.faredist.hrgi.Hrgi02FareDistService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object HrgiFareDistModules {
    val hrgiFareDistKoinModules = module {

        // service
        singleOf(::Hrgi01FareDistService)
        singleOf(::Hrgi02FareDistService)

        // controller
        singleOf(::Hrgi01FareDistController)

    }
}
