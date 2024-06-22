package net.konohana.sakuya.yoshun.dimodules

import net.konohana.sakuya.yoshun.controller.lilie.Lilie01Controller
import net.konohana.sakuya.yoshun.controller.lilie.Lilie02Controller
import net.konohana.sakuya.yoshun.controller.lilie.Lilie03Controller
import net.konohana.sakuya.yoshun.controller.lilie.Lilie04Controller
import net.konohana.sakuya.yoshun.controller.lilie.Lilie05Controller
import net.konohana.sakuya.yoshun.controller.lilie.Lilie06Controller
import net.konohana.sakuya.yoshun.services.lilie.Lilie01Service
import net.konohana.sakuya.yoshun.services.lilie.Lilie02Service
import net.konohana.sakuya.yoshun.services.lilie.Lilie03Service
import net.konohana.sakuya.yoshun.services.lilie.Lilie04Service
import net.konohana.sakuya.yoshun.services.lilie.Lilie05Service
import net.konohana.sakuya.yoshun.services.lilie.Lilie06Service
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object LilieModules {
    val lilieKoinModules = module {

        // service
        singleOf(::Lilie01Service)
        singleOf(::Lilie02Service)
        singleOf(::Lilie03Service)
        singleOf(::Lilie04Service)
        singleOf(::Lilie05Service)
        singleOf(::Lilie06Service)

        // controller
        singleOf(::Lilie01Controller)
        singleOf(::Lilie02Controller)
        singleOf(::Lilie03Controller)
        singleOf(::Lilie04Controller)
        singleOf(::Lilie05Controller)
        singleOf(::Lilie06Controller)
    }
}
