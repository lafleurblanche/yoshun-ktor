package net.konohana.sakuya.yoshun.controller.quadra

import net.konohana.sakuya.yoshun.dtos.quadra.Quadra26Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra26FrontendDto
import net.konohana.sakuya.yoshun.services.quadra.Quadra26Service

class Quadra26Controller(
    private val quadra26Service: Quadra26Service
) {
    suspend fun getQuadra26StaList(): List<Quadra26Dto> {
        return quadra26Service.getQuadra26()
    }

    suspend fun getQuadra26StaListByStaCode(staCode: String): Quadra26Dto? {
        return quadra26Service.getQuadra26ByStaCode(staCode = staCode)
    }

    suspend fun getQuadra26FrontendList(): List<Quadra26FrontendDto> {
        // サービス層の新しいメソッドを呼び出す
        return quadra26Service.getQuadra26Frontend()
    }
}
