package net.konohana.sakuya.yoshun.controller.gate

import net.konohana.sakuya.yoshun.dtos.gate.Gate03Dto
import net.konohana.sakuya.yoshun.services.gate.Gate03Service

class Gate03Controller(
    private val gate03Service: Gate03Service
) {
    suspend fun getGate03StaList(): List<Gate03Dto> {
        return gate03Service.getGate03()
    }

    suspend fun getGate03StaListByStaCode(staCode: String): Gate03Dto? {
        return gate03Service.getGate03ByStaCode(staCode = staCode)
    }
}
