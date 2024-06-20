package net.konohana.sakuya.yoshun

import io.ktor.server.application.Application
import net.konohana.sakuya.yoshun.plugins.configureHTTP
import net.konohana.sakuya.yoshun.plugins.configureRouting
import net.konohana.sakuya.yoshun.plugins.configureSerialization
import net.konohana.sakuya.yoshun.plugins.settingKoin


fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureHTTP()
    configureRouting()
    settingKoin()
}
