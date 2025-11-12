package net.konohana.sakuya.yoshun.controller.quadra

import net.konohana.sakuya.yoshun.dtos.quadra.Quadra14Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra14FrontendDto
import net.konohana.sakuya.yoshun.services.quadra.Quadra14Service

class Quadra14Controller(
    private val quadra14Service: Quadra14Service
) {
    suspend fun getQuadra14StaList(): List<Quadra14Dto> {
        return quadra14Service.getQuadra14()
    }

    suspend fun getQuadra14StaListByStaCode(staCode: String): Quadra14Dto? {
        return quadra14Service.getQuadra14ByStaCode(staCode = staCode)
    }

    suspend fun getQuadra14FrontendList(): List<Quadra14FrontendDto> {
        // サービス層の新しいメソッドを呼び出す
        return quadra14Service.getQuadra14Frontend()
    }
}
