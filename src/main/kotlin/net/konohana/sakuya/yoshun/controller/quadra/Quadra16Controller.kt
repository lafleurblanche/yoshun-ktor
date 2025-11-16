package net.konohana.sakuya.yoshun.controller.quadra

import net.konohana.sakuya.yoshun.dtos.quadra.Quadra16Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra16FrontendDto
import net.konohana.sakuya.yoshun.services.quadra.Quadra16Service

class Quadra16Controller(
    private val quadra16Service: Quadra16Service
) {
    suspend fun getQuadra16StaList(): List<Quadra16Dto> {
        return quadra16Service.getQuadra16()
    }

    suspend fun getQuadra16StaListByStaCode(staCode: String): Quadra16Dto? {
        return quadra16Service.getQuadra16ByStaCode(staCode = staCode)
    }

    suspend fun getQuadra16FrontendList(): List<Quadra16FrontendDto> {
        // サービス層の新しいメソッドを呼び出す
        return quadra16Service.getQuadra16Frontend()
    }
}
