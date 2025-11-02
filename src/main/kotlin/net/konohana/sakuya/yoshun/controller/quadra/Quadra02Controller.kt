package net.konohana.sakuya.yoshun.controller.quadra

import net.konohana.sakuya.yoshun.dtos.quadra.Quadra02Dto
import net.konohana.sakuya.yoshun.services.quadra.Quadra02Service

class Quadra02Controller(
    private val quadra02Service: Quadra02Service
) {
    suspend fun getQuadra02StaList(): List<Quadra02Dto> {
        return quadra02Service.getQuadra02()
    }

    suspend fun getQuadra02StaListByStaCode(staCode: String): Quadra02Dto? {
        return quadra02Service.getQuadra02ByStaCode(staCode = staCode)
    }
}
