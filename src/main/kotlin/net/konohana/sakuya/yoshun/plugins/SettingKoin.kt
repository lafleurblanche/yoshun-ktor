package net.konohana.sakuya.yoshun.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import net.konohana.sakuya.yoshun.dimodules.ArgwModules
import net.konohana.sakuya.yoshun.dimodules.EnjuModules
import net.konohana.sakuya.yoshun.dimodules.GateModules
import net.konohana.sakuya.yoshun.dimodules.HrgiModules
import net.konohana.sakuya.yoshun.dimodules.LilieModules
import net.konohana.sakuya.yoshun.dimodules.LocBModules
import net.konohana.sakuya.yoshun.dimodules.LocModules
import net.konohana.sakuya.yoshun.dimodules.NeueModules
import net.konohana.sakuya.yoshun.dimodules.ThirdModules
import org.koin.ktor.plugin.Koin

fun Application.settingKoin() {
    install(Koin) {
        modules(
            ArgwModules.argwKoinModules,
            EnjuModules.enjuKoinModules,
            GateModules.gateKoinModules,
            HrgiModules.hrgiKoinModules,
            LocModules.locKoinModules,
            LocBModules.locBKoinModules,
            NeueModules.neueKoinModules,
            LilieModules.lilieKoinModules,
            ThirdModules.thirdKoinModule,
        )
    }
}
