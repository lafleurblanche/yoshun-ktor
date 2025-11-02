package net.konohana.sakuya.yoshun.controller.quadra

import net.konohana.sakuya.yoshun.dtos.quadra.Quadra09Dto
import net.konohana.sakuya.yoshun.services.quadra.Quadra09Service

class Quadra09Controller(
    private val quadra09Service: Quadra09Service
) {
    suspend fun getQuadra09StaList(): List<Quadra09Dto> {
        return quadra09Service.getQuadra09()
    }

    suspend fun getQuadra09StaListByStaCode(staCode: String): Quadra09Dto? {
        return quadra09Service.getQuadra09ByStaCode(staCode = staCode)
    }
}
