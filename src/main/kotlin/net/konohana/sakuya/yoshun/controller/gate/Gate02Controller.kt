package net.konohana.sakuya.yoshun.controller.gate

import net.konohana.sakuya.yoshun.dtos.gate.Gate02Dto
import net.konohana.sakuya.yoshun.services.gate.Gate02Service

class Gate02Controller(
    private val gate02Service: Gate02Service
) {
    suspend fun getGate02StaList(): List<Gate02Dto> {
        return gate02Service.getGate02()
    }
}
