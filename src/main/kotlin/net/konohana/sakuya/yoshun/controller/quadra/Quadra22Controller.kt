package net.konohana.sakuya.yoshun.controller.quadra

import net.konohana.sakuya.yoshun.dtos.quadra.Quadra22Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra22FrontendDto
import net.konohana.sakuya.yoshun.services.quadra.Quadra22Service

class Quadra22Controller(
    private val quadra22Service: Quadra22Service
) {
    suspend fun getQuadra22StaList(): List<Quadra22Dto> {
        return quadra22Service.getQuadra22()
    }

    suspend fun getQuadra22StaListByStaCode(staCode: String): Quadra22Dto? {
        return quadra22Service.getQuadra22ByStaCode(staCode = staCode)
    }

    suspend fun getQuadra22FrontendList(): List<Quadra22FrontendDto> {
        // サービス層の新しいメソッドを呼び出す
        return quadra22Service.getQuadra22Frontend()
    }
}
