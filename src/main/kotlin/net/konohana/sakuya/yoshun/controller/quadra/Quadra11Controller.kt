package net.konohana.sakuya.yoshun.controller.quadra

import net.konohana.sakuya.yoshun.dtos.quadra.Quadra11Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra11FrontendDto
import net.konohana.sakuya.yoshun.services.quadra.Quadra11Service

class Quadra11Controller(
    private val quadra11Service: Quadra11Service
) {
    suspend fun getQuadra11StaList(): List<Quadra11Dto> {
        return quadra11Service.getQuadra11()
    }

    suspend fun getQuadra11StaListByStaCode(staCode: String): Quadra11Dto? {
        return quadra11Service.getQuadra11ByStaCode(staCode = staCode)
    }

    suspend fun getQuadra11FrontendList(): List<Quadra11FrontendDto> {
        // サービス層の新しいメソッドを呼び出す
        return quadra11Service.getQuadra11Frontend()
    }
}
