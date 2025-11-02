package net.konohana.sakuya.yoshun.controller.quadra

import net.konohana.sakuya.yoshun.dtos.quadra.Quadra08Dto
import net.konohana.sakuya.yoshun.services.quadra.Quadra08Service

class Quadra08Controller(
    private val quadra08Service: Quadra08Service
) {
    suspend fun getQuadra08StaList(): List<Quadra08Dto> {
        return quadra08Service.getQuadra08()
    }

    suspend fun getQuadra08StaListByStaCode(staCode: String): Quadra08Dto? {
        return quadra08Service.getQuadra08ByStaCode(staCode = staCode)
    }
}
