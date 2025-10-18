package net.konohana.sakuya.yoshun.dimodules

import net.konohana.sakuya.yoshun.controller.hnki.Hnki01Controller
import net.konohana.sakuya.yoshun.controller.hnki.Hnki02Controller
import net.konohana.sakuya.yoshun.controller.hnki.Hnki03Controller
import net.konohana.sakuya.yoshun.controller.hnki.Hnki04Controller
import net.konohana.sakuya.yoshun.services.hnki.Hnki01Service
import net.konohana.sakuya.yoshun.services.hnki.Hnki02Service
import net.konohana.sakuya.yoshun.services.hnki.Hnki03Service
import net.konohana.sakuya.yoshun.services.hnki.Hnki04Service
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object HnkiModules {
    val hnkiKoinModules = module {

        // service
        singleOf(::Hnki01Service)
        singleOf(::Hnki02Service)
        singleOf(::Hnki03Service)
        singleOf(::Hnki04Service)

        // controller
        singleOf(::Hnki01Controller)
        singleOf(::Hnki02Controller)
        singleOf(::Hnki03Controller)
        singleOf(::Hnki04Controller)
    }
}
