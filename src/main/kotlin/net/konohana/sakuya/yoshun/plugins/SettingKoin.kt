package net.konohana.sakuya.yoshun.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import net.konohana.sakuya.yoshun.dimodules.ArgwModules
import net.konohana.sakuya.yoshun.dimodules.EnjuModules
import net.konohana.sakuya.yoshun.dimodules.LilieModules
import net.konohana.sakuya.yoshun.dimodules.NeueModules
import net.konohana.sakuya.yoshun.dimodules.ThirdModules
import net.konohana.sakuya.yoshun.dimodules.faredist.ArgwFareDistModules
import net.konohana.sakuya.yoshun.dimodules.faredist.HrgiFareDistModules
import net.konohana.sakuya.yoshun.dimodules.faredist.NeueFareDistModules
import org.koin.ktor.plugin.Koin

fun Application.settingKoin() {
    install(Koin) {
        modules(
            ArgwModules.argwKoinModules,
            EnjuModules.enjuKoinModules,
            NeueModules.neueKoinModules,
            LilieModules.lilieKoinModules,
            ThirdModules.thirdKoinModule,
            ArgwFareDistModules.argwFareDistKoinModules,
            HrgiFareDistModules.hrgiFareDistKoinModules,
            NeueFareDistModules.neueFareDistKoinModules,
        )
    }
}
