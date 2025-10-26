package net.konohana.sakuya.yoshun.controller.gate

import net.konohana.sakuya.yoshun.dtos.gate.Gate04Dto
import net.konohana.sakuya.yoshun.services.gate.Gate04Service

class Gate04Controller(
    private val gate04Service: Gate04Service
) {
    suspend fun getGate04StaList(): List<Gate04Dto> {
        return gate04Service.getGate04()
    }
}
