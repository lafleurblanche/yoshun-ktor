package net.konohana.sakuya.yoshun.controller.quadra

import net.konohana.sakuya.yoshun.dtos.quadra.Quadra18Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra18FrontendDto
import net.konohana.sakuya.yoshun.services.quadra.Quadra18Service

class Quadra18Controller(
    private val quadra18Service: Quadra18Service
) {
    suspend fun getQuadra18StaList(): List<Quadra18Dto> {
        return quadra18Service.getQuadra18()
    }

    suspend fun getQuadra18StaListByStaCode(staCode: String): Quadra18Dto? {
        return quadra18Service.getQuadra18ByStaCode(staCode = staCode)
    }

    suspend fun getQuadra18FrontendList(): List<Quadra18FrontendDto> {
        // サービス層の新しいメソッドを呼び出す
        return quadra18Service.getQuadra18Frontend()
    }
}
