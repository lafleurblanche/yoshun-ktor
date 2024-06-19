package net.konohana.sakuya.yoshun

import io.ktor.server.application.*
import net.konohana.sakuya.yoshun.plugins.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureHTTP()
    configureRouting()
}
