package net.konohana.sakuya.yoshun.controller.quadra

import net.konohana.sakuya.yoshun.dtos.quadra.Quadra24Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra24FrontendDto
import net.konohana.sakuya.yoshun.services.quadra.Quadra24Service

class Quadra24Controller(
    private val quadra24Service: Quadra24Service
) {
    suspend fun getQuadra24StaList(): List<Quadra24Dto> {
        return quadra24Service.getQuadra24()
    }

    suspend fun getQuadra24StaListByStaCode(staCode: String): Quadra24Dto? {
        return quadra24Service.getQuadra24ByStaCode(staCode = staCode)
    }

    suspend fun getQuadra24FrontendList(): List<Quadra24FrontendDto> {
        // サービス層の新しいメソッドを呼び出す
        return quadra24Service.getQuadra24Frontend()
    }
}
