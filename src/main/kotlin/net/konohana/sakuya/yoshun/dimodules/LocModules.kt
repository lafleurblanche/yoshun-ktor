package net.konohana.sakuya.yoshun.dimodules

import net.konohana.sakuya.yoshun.controller.loc.Loc01Controller
import net.konohana.sakuya.yoshun.controller.loc.Loc02Controller
import net.konohana.sakuya.yoshun.controller.loc.Loc03Controller
import net.konohana.sakuya.yoshun.controller.loc.Loc04Controller
import net.konohana.sakuya.yoshun.controller.loc.Loc05Controller
import net.konohana.sakuya.yoshun.controller.loc.Loc06Controller
import net.konohana.sakuya.yoshun.controller.loc.Loc07Controller
import net.konohana.sakuya.yoshun.controller.loc.Loc08Controller
import net.konohana.sakuya.yoshun.controller.loc.Loc09Controller
import net.konohana.sakuya.yoshun.controller.loc.Loc10Controller
import net.konohana.sakuya.yoshun.controller.loc.Loc11Controller
import net.konohana.sakuya.yoshun.services.loc.Loc01Service
import net.konohana.sakuya.yoshun.services.loc.Loc02Service
import net.konohana.sakuya.yoshun.services.loc.Loc03Service
import net.konohana.sakuya.yoshun.services.loc.Loc04Service
import net.konohana.sakuya.yoshun.services.loc.Loc05Service
import net.konohana.sakuya.yoshun.services.loc.Loc06Service
import net.konohana.sakuya.yoshun.services.loc.Loc07Service
import net.konohana.sakuya.yoshun.services.loc.Loc08Service
import net.konohana.sakuya.yoshun.services.loc.Loc09Service
import net.konohana.sakuya.yoshun.services.loc.Loc10Service
import net.konohana.sakuya.yoshun.services.loc.Loc11Service
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object LocModules {
    val locKoinModules = module {

        // service
        singleOf(::Loc01Service)
        singleOf(::Loc02Service)
        singleOf(::Loc03Service)
        singleOf(::Loc04Service)
        singleOf(::Loc05Service)
        singleOf(::Loc06Service)
        singleOf(::Loc07Service)
        singleOf(::Loc08Service)
        singleOf(::Loc09Service)
        singleOf(::Loc10Service)
        singleOf(::Loc11Service)

        // controller
        singleOf(::Loc01Controller)
        singleOf(::Loc02Controller)
        singleOf(::Loc03Controller)
        singleOf(::Loc04Controller)
        singleOf(::Loc05Controller)
        singleOf(::Loc06Controller)
        singleOf(::Loc07Controller)
        singleOf(::Loc08Controller)
        singleOf(::Loc09Controller)
        singleOf(::Loc10Controller)
        singleOf(::Loc11Controller)

    }

}
