package net.konohana.sakuya.yoshun.controller.quadra

import net.konohana.sakuya.yoshun.dtos.quadra.Quadra05Dto
import net.konohana.sakuya.yoshun.services.quadra.Quadra05Service

class Quadra05Controller(
    private val quadra05Service: Quadra05Service
) {
    suspend fun getQuadra05StaList(): List<Quadra05Dto> {
        return quadra05Service.getQuadra05()
    }

    suspend fun getQuadra05StaListByStaCode(staCode: String): Quadra05Dto? {
        return quadra05Service.getQuadra05ByStaCode(staCode = staCode)
    }
}
