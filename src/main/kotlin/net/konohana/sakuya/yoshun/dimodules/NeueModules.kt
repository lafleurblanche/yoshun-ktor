package net.konohana.sakuya.yoshun.dimodules

import net.konohana.sakuya.yoshun.controller.neue.Neue01Controller
import net.konohana.sakuya.yoshun.controller.neue.Neue02Controller
import net.konohana.sakuya.yoshun.controller.neue.Neue03Controller
import net.konohana.sakuya.yoshun.controller.neue.Neue04Controller
import net.konohana.sakuya.yoshun.controller.neue.Neue05Controller
import net.konohana.sakuya.yoshun.controller.neue.Neue06Controller
import net.konohana.sakuya.yoshun.controller.neue.Neue07Controller
import net.konohana.sakuya.yoshun.controller.neue.Neue08Controller
import net.konohana.sakuya.yoshun.services.neue.Neue01Service
import net.konohana.sakuya.yoshun.services.neue.Neue02Service
import net.konohana.sakuya.yoshun.services.neue.Neue03Service
import net.konohana.sakuya.yoshun.services.neue.Neue04Service
import net.konohana.sakuya.yoshun.services.neue.Neue05Service
import net.konohana.sakuya.yoshun.services.neue.Neue06Service
import net.konohana.sakuya.yoshun.services.neue.Neue07Service
import net.konohana.sakuya.yoshun.services.neue.Neue08Service
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object NeueModules {
    val neueKoinModules = module {

        // service
        singleOf(::Neue01Service)
        singleOf(::Neue02Service)
        singleOf(::Neue03Service)
        singleOf(::Neue04Service)
        singleOf(::Neue05Service)
        singleOf(::Neue06Service)
        singleOf(::Neue07Service)
        singleOf(::Neue08Service)

        // controller
        singleOf(::Neue01Controller)
        singleOf(::Neue02Controller)
        singleOf(::Neue03Controller)
        singleOf(::Neue04Controller)
        singleOf(::Neue05Controller)
        singleOf(::Neue06Controller)
        singleOf(::Neue07Controller)
        singleOf(::Neue08Controller)
    }
}
