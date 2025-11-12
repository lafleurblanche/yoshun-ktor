package net.konohana.sakuya.yoshun.controller.quadra

import net.konohana.sakuya.yoshun.dtos.quadra.Quadra12Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra12FrontendDto
import net.konohana.sakuya.yoshun.services.quadra.Quadra12Service

class Quadra12Controller(
    private val quadra12Service: Quadra12Service
) {
    suspend fun getQuadra12StaList(): List<Quadra12Dto> {
        return quadra12Service.getQuadra12()
    }

    suspend fun getQuadra12StaListByStaCode(staCode: String): Quadra12Dto? {
        return quadra12Service.getQuadra12ByStaCode(staCode = staCode)
    }

    suspend fun getQuadra12FrontendList(): List<Quadra12FrontendDto> {
        // サービス層の新しいメソッドを呼び出す
        return quadra12Service.getQuadra12Frontend()
    }
}
