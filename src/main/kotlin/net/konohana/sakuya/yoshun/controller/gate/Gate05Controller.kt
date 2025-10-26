package net.konohana.sakuya.yoshun.controller.gate

import net.konohana.sakuya.yoshun.dtos.gate.Gate05Dto
import net.konohana.sakuya.yoshun.services.gate.Gate05Service

class Gate05Controller(
    private val gate05Service: Gate05Service
) {
    suspend fun getGate05StaList(): List<Gate05Dto> {
        return gate05Service.getGate05()
    }
}
