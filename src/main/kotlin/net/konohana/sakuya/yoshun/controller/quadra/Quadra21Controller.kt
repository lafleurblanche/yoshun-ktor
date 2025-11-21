package net.konohana.sakuya.yoshun.controller.quadra

import net.konohana.sakuya.yoshun.dtos.quadra.Quadra21Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra21FrontendDto
import net.konohana.sakuya.yoshun.services.quadra.Quadra21Service

class Quadra21Controller(
    private val quadra21Service: Quadra21Service
) {
    suspend fun getQuadra21StaList(): List<Quadra21Dto> {
        return quadra21Service.getQuadra21()
    }

    suspend fun getQuadra21StaListByStaCode(staCode: String): Quadra21Dto? {
        return quadra21Service.getQuadra21ByStaCode(staCode = staCode)
    }

    suspend fun getQuadra21FrontendList(): List<Quadra21FrontendDto> {
        // サービス層の新しいメソッドを呼び出す
        return quadra21Service.getQuadra21Frontend()
    }
}
