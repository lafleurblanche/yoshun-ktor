package net.konohana.sakuya.yoshun.controller.quadra

import net.konohana.sakuya.yoshun.dtos.quadra.Quadra09FrontendDto
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

    suspend fun getQuadra09FrontendList(): List<Quadra09FrontendDto> {
        // サービス層の新しいメソッドを呼び出す
        return quadra09Service.getQuadra09Frontend()
    }
}
