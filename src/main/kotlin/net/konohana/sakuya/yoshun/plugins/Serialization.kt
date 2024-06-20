package net.konohana.sakuya.yoshun.plugins

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.serialization.jackson.jackson
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.routing
import net.konohana.sakuya.yoshun.router.argw.argwRouter

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
    routing {
        argwRouter()
    }
}
