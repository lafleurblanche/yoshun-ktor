package net.konohana.sakuya.yoshun.controller.quadra

import net.konohana.sakuya.yoshun.dtos.quadra.Quadra03Dto
import net.konohana.sakuya.yoshun.services.quadra.Quadra03Service

class Quadra03Controller(
    private val quadra03Service: Quadra03Service
) {
    suspend fun getQuadra03StaList(): List<Quadra03Dto> {
        return quadra03Service.getQuadra03()
    }

    suspend fun getQuadra03StaListByStaCode(staCode: String): Quadra03Dto? {
        return quadra03Service.getQuadra03ByStaCode(staCode = staCode)
    }
}
