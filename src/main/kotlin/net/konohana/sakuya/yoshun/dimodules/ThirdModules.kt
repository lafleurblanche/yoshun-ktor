package net.konohana.sakuya.yoshun.dimodules

import net.konohana.sakuya.yoshun.controller.third.Third01Controller
import net.konohana.sakuya.yoshun.controller.third.Third02Controller
import net.konohana.sakuya.yoshun.controller.third.Third03Controller
import net.konohana.sakuya.yoshun.controller.third.Third04Controller
import net.konohana.sakuya.yoshun.controller.third.Third05Controller
import net.konohana.sakuya.yoshun.controller.third.Third06Controller
import net.konohana.sakuya.yoshun.controller.third.Third07Controller
import net.konohana.sakuya.yoshun.controller.third.Third08Controller
import net.konohana.sakuya.yoshun.controller.third.Third09Controller
import net.konohana.sakuya.yoshun.controller.third.Third10Controller
import net.konohana.sakuya.yoshun.services.third.Third01Service
import net.konohana.sakuya.yoshun.services.third.Third02Service
import net.konohana.sakuya.yoshun.services.third.Third03Service
import net.konohana.sakuya.yoshun.services.third.Third04Service
import net.konohana.sakuya.yoshun.services.third.Third05Service
import net.konohana.sakuya.yoshun.services.third.Third06Service
import net.konohana.sakuya.yoshun.services.third.Third07Service
import net.konohana.sakuya.yoshun.services.third.Third08Service
import net.konohana.sakuya.yoshun.services.third.Third09Service
import net.konohana.sakuya.yoshun.services.third.Third10Service
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object ThirdModules {
    val thirdKoinModule = module {
        // service
        singleOf(::Third01Service)
        singleOf(::Third02Service)
        singleOf(::Third03Service)
        singleOf(::Third04Service)
        singleOf(::Third05Service)
        singleOf(::Third06Service)
        singleOf(::Third07Service)
        singleOf(::Third08Service)
        singleOf(::Third09Service)
        singleOf(::Third10Service)

        // controller
        singleOf(::Third01Controller)
        singleOf(::Third02Controller)
        singleOf(::Third03Controller)
        singleOf(::Third04Controller)
        singleOf(::Third05Controller)
        singleOf(::Third06Controller)
        singleOf(::Third07Controller)
        singleOf(::Third08Controller)
        singleOf(::Third09Controller)
        singleOf(::Third10Controller)
    }
}
