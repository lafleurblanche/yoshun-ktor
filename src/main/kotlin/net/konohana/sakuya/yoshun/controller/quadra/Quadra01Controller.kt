package net.konohana.sakuya.yoshun.controller.quadra

import net.konohana.sakuya.yoshun.dtos.quadra.Quadra01Dto
import net.konohana.sakuya.yoshun.services.quadra.Quadra01Service

class Quadra01Controller(
    private val quadra01Service: Quadra01Service
) {
    suspend fun getQuadra01StaList(): List<Quadra01Dto> {
        return quadra01Service.getQuadra01()
    }

    suspend fun getQuadra01StaListByStaCode(staCode: String): Quadra01Dto? {
        return quadra01Service.getQuadra01ByStaCode(staCode = staCode)
    }
}
