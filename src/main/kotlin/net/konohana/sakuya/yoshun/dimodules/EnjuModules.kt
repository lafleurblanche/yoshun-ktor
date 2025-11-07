package net.konohana.sakuya.yoshun.dimodules

import net.konohana.sakuya.yoshun.controller.enju.Enju01Controller
import net.konohana.sakuya.yoshun.controller.enju.Enju02Controller
import net.konohana.sakuya.yoshun.controller.enju.Enju03Controller
import net.konohana.sakuya.yoshun.controller.enju.Enju04Controller
import net.konohana.sakuya.yoshun.controller.enju.Enju05Controller
import net.konohana.sakuya.yoshun.controller.enju.Enju06Controller
import net.konohana.sakuya.yoshun.controller.enju.Enju07Controller
import net.konohana.sakuya.yoshun.controller.enju.Enju08Controller
import net.konohana.sakuya.yoshun.controller.enju.Enju09Controller
import net.konohana.sakuya.yoshun.controller.enju.Enju10Controller
import net.konohana.sakuya.yoshun.controller.enju.Enju11Controller
import net.konohana.sakuya.yoshun.services.enju.Enju01Service
import net.konohana.sakuya.yoshun.services.enju.Enju02Service
import net.konohana.sakuya.yoshun.services.enju.Enju03Service
import net.konohana.sakuya.yoshun.services.enju.Enju04Service
import net.konohana.sakuya.yoshun.services.enju.Enju05Service
import net.konohana.sakuya.yoshun.services.enju.Enju06Service
import net.konohana.sakuya.yoshun.services.enju.Enju07Service
import net.konohana.sakuya.yoshun.services.enju.Enju08Service
import net.konohana.sakuya.yoshun.services.enju.Enju09Service
import net.konohana.sakuya.yoshun.services.enju.Enju10Service
import net.konohana.sakuya.yoshun.services.enju.Enju11Service
import net.konohana.sakuya.yoshun.services.enju.Enju12Service
import net.konohana.sakuya.yoshun.services.enju.Enju13Service
import net.konohana.sakuya.yoshun.services.enju.Enju14Service
import net.konohana.sakuya.yoshun.services.enju.Enju15Service
import net.konohana.sakuya.yoshun.services.enju.Enju16Service
import net.konohana.sakuya.yoshun.services.enju.Enju17Service
import net.konohana.sakuya.yoshun.services.enju.Enju18Service
import net.konohana.sakuya.yoshun.services.enju.Enju19Service
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object EnjuModules {
    val enjuKoinModules = module {

        // service
        singleOf(::Enju01Service)
        singleOf(::Enju02Service)
        singleOf(::Enju03Service)
        singleOf(::Enju04Service)
        singleOf(::Enju05Service)
        singleOf(::Enju06Service)
        singleOf(::Enju07Service)
        singleOf(::Enju08Service)
        singleOf(::Enju09Service)
        singleOf(::Enju10Service)
        singleOf(::Enju11Service)
        singleOf(::Enju12Service)
        singleOf(::Enju13Service)
        singleOf(::Enju14Service)
        singleOf(::Enju15Service)
        singleOf(::Enju16Service)
        singleOf(::Enju17Service)
        singleOf(::Enju18Service)
        singleOf(::Enju19Service)


        // controller
        singleOf(::Enju01Controller)
        singleOf(::Enju02Controller)
        singleOf(::Enju03Controller)
        singleOf(::Enju04Controller)
        singleOf(::Enju05Controller)
        singleOf(::Enju06Controller)
        singleOf(::Enju07Controller)
        singleOf(::Enju08Controller)
        singleOf(::Enju09Controller)
        singleOf(::Enju10Controller)
        singleOf(::Enju11Controller)
    }
}
