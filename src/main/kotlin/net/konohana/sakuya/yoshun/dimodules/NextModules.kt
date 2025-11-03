package net.konohana.sakuya.yoshun.dimodules

import net.konohana.sakuya.yoshun.controller.next.Next01Controller
import net.konohana.sakuya.yoshun.controller.next.Next02Controller
import net.konohana.sakuya.yoshun.controller.next.Next03Controller
import net.konohana.sakuya.yoshun.controller.next.Next04Controller
import net.konohana.sakuya.yoshun.controller.next.Next05Controller
import net.konohana.sakuya.yoshun.services.next.Next01Service
import net.konohana.sakuya.yoshun.services.next.Next02Service
import net.konohana.sakuya.yoshun.services.next.Next03Service
import net.konohana.sakuya.yoshun.services.next.Next04Service
import net.konohana.sakuya.yoshun.services.next.Next05Service
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object NextModules {
    val nextKoinModules = module {

        // service
        singleOf(::Next01Service)
        singleOf(::Next02Service)
        singleOf(::Next03Service)
        singleOf(::Next04Service)
        singleOf(::Next05Service)

        // controller
        singleOf(::Next01Controller)
        singleOf(::Next02Controller)
        singleOf(::Next03Controller)
        singleOf(::Next04Controller)
        singleOf(::Next05Controller)

    }
}
