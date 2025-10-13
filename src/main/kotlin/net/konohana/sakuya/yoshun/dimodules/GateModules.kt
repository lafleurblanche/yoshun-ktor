package net.konohana.sakuya.yoshun.dimodules

import net.konohana.sakuya.yoshun.services.gate.Gate01Service
import net.konohana.sakuya.yoshun.services.gate.Gate02Service
import net.konohana.sakuya.yoshun.services.gate.Gate03Service
import net.konohana.sakuya.yoshun.services.gate.Gate04Service
import net.konohana.sakuya.yoshun.services.gate.Gate05Service
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object GateModules {
    val gateKoinModules = module {

        // service
        singleOf(::Gate01Service)
        singleOf(::Gate02Service)
        singleOf(::Gate03Service)
        singleOf(::Gate04Service)
        singleOf(::Gate05Service)

        // controller

    }
}
