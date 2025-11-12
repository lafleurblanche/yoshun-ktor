package net.konohana.sakuya.yoshun.controller.quadra

import net.konohana.sakuya.yoshun.dtos.quadra.Quadra13Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra13FrontendDto
import net.konohana.sakuya.yoshun.services.quadra.Quadra13Service

class Quadra13Controller(
    private val quadra13Service: Quadra13Service
) {
    suspend fun getQuadra13StaList(): List<Quadra13Dto> {
        return quadra13Service.getQuadra13()
    }

    suspend fun getQuadra13StaListByStaCode(staCode: String): Quadra13Dto? {
        return quadra13Service.getQuadra13ByStaCode(staCode = staCode)
    }

    suspend fun getQuadra13FrontendList(): List<Quadra13FrontendDto> {
        // サービス層の新しいメソッドを呼び出す
        return quadra13Service.getQuadra13Frontend()
    }
}
