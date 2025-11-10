package net.konohana.sakuya.yoshun.controller.quadra

import net.konohana.sakuya.yoshun.dtos.quadra.Quadra04FrontendDto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra04Dto
import net.konohana.sakuya.yoshun.services.quadra.Quadra04Service

class Quadra04Controller(
    private val quadra04Service: Quadra04Service
) {
    suspend fun getQuadra04StaList(): List<Quadra04Dto> {
        return quadra04Service.getQuadra04()
    }

    suspend fun getQuadra04StaListByStaCode(staCode: String): Quadra04Dto? {
        return quadra04Service.getQuadra04ByStaCode(staCode = staCode)
    }

    suspend fun getQuadra04FrontendList(): List<Quadra04FrontendDto> {
        // サービス層の新しいメソッドを呼び出す
        return quadra04Service.getQuadra04Frontend()
    }
}
