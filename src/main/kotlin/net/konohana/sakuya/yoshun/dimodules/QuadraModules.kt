package net.konohana.sakuya.yoshun.dimodules

import net.konohana.sakuya.yoshun.services.quadra.Quadra01Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra02Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra03Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra04Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra05Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra06Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra07Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra08Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra09Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra10Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra11Service
import net.konohana.sakuya.yoshun.services.quadra.Quadra12Service
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object QuadraModules {
    val quadraKoinModules = module {

        // services
        singleOf(::Quadra01Service)
        singleOf(::Quadra02Service)
        singleOf(::Quadra03Service)
        singleOf(::Quadra04Service)
        singleOf(::Quadra05Service)
        singleOf(::Quadra06Service)
        singleOf(::Quadra07Service)
        singleOf(::Quadra08Service)
        singleOf(::Quadra09Service)
        singleOf(::Quadra10Service)
        singleOf(::Quadra11Service)
        singleOf(::Quadra12Service)

        // controllers

    }
}
