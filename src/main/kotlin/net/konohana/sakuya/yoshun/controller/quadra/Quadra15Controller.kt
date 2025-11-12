package net.konohana.sakuya.yoshun.controller.quadra

import net.konohana.sakuya.yoshun.dtos.quadra.Quadra15Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra15FrontendDto
import net.konohana.sakuya.yoshun.services.quadra.Quadra15Service

class Quadra15Controller(
    private val quadra15Service: Quadra15Service
) {
    suspend fun getQuadra15StaList(): List<Quadra15Dto> {
        return quadra15Service.getQuadra15()
    }

    suspend fun getQuadra15StaListByStaCode(staCode: String): Quadra15Dto? {
        return quadra15Service.getQuadra15ByStaCode(staCode = staCode)
    }

    suspend fun getQuadra15FrontendList(): List<Quadra15FrontendDto> {
        // サービス層の新しいメソッドを呼び出す
        return quadra15Service.getQuadra15Frontend()
    }
}
