package net.konohana.sakuya.yoshun.controller.quadra

import net.konohana.sakuya.yoshun.dtos.quadra.Quadra07Dto
import net.konohana.sakuya.yoshun.services.quadra.Quadra07Service

class Quadra07Controller(
    private val quadra07Service: Quadra07Service
) {
    suspend fun getQuadra07StaList(): List<Quadra07Dto> {
        return quadra07Service.getQuadra07()
    }

    suspend fun getQuadra07StaListByStaCode(staCode: String): Quadra07Dto? {
        return quadra07Service.getQuadra07ByStaCode(staCode = staCode)
    }
}
