package net.konohana.sakuya.yoshun.dimodules

import net.konohana.sakuya.yoshun.services.third.Third01Service
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object ThirdModules {
    val thirdKoinModule = module {
        // service
        singleOf(::Third01Service)
    }
}
