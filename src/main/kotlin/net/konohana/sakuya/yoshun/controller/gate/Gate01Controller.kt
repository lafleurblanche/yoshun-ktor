package net.konohana.sakuya.yoshun.controller.gate

import net.konohana.sakuya.yoshun.dtos.gate.Gate01Dto
import net.konohana.sakuya.yoshun.services.gate.Gate01Service

class Gate01Controller(
    private val gate01Service: Gate01Service
) {
    suspend fun getGate01StaList(): List<Gate01Dto> {
        return gate01Service.getGate01()
    }

    suspend fun getGate01StaListByStaCode(staCode: String): Gate01Dto? {
        return gate01Service.getGate01ByStaCode(staCode = staCode)
    }
}
