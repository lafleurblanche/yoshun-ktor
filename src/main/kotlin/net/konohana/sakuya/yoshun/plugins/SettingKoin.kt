package net.konohana.sakuya.yoshun.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import net.konohana.sakuya.yoshun.dimodules.ArgwModules
import net.konohana.sakuya.yoshun.dimodules.EnjuModules
import net.konohana.sakuya.yoshun.dimodules.LilieModules
import net.konohana.sakuya.yoshun.dimodules.NeueModules
import org.koin.ktor.plugin.Koin

fun Application.settingKoin() {
    install(Koin) {
        modules(
            ArgwModules.argwKoinModules,
            EnjuModules.enjuKoinModules,
            NeueModules.neueKoinModules,
            LilieModules.lilieKoinModules,
        )
    }
}
