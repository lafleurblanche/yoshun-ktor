package net.konohana.sakuya.yoshun.controller.quadra

import net.konohana.sakuya.yoshun.dtos.quadra.Quadra17Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra17FrontendDto
import net.konohana.sakuya.yoshun.services.quadra.Quadra17Service

class Quadra17Controller(
    private val quadra17Service: Quadra17Service
) {
    suspend fun getQuadra17StaList(): List<Quadra17Dto> {
        return quadra17Service.getQuadra17()
    }

    suspend fun getQuadra17StaListByStaCode(staCode: String): Quadra17Dto? {
        return quadra17Service.getQuadra17ByStaCode(staCode = staCode)
    }

    suspend fun getQuadra17FrontendList(): List<Quadra17FrontendDto> {
        // サービス層の新しいメソッドを呼び出す
        return quadra17Service.getQuadra17Frontend()
    }
}
