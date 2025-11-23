package net.konohana.sakuya.yoshun.controller.quadra

import net.konohana.sakuya.yoshun.dtos.quadra.Quadra25Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra25FrontendDto
import net.konohana.sakuya.yoshun.services.quadra.Quadra25Service

class Quadra25Controller(
    private val quadra25Service: Quadra25Service
) {
    suspend fun getQuadra25StaList(): List<Quadra25Dto> {
        return quadra25Service.getQuadra25()
    }

    suspend fun getQuadra25StaListByStaCode(staCode: String): Quadra25Dto? {
        return quadra25Service.getQuadra25ByStaCode(staCode = staCode)
    }

    suspend fun getQuadra25FrontendList(): List<Quadra25FrontendDto> {
        // サービス層の新しいメソッドを呼び出す
        return quadra25Service.getQuadra25Frontend()
    }
}
