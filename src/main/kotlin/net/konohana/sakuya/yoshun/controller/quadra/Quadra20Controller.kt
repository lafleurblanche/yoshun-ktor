package net.konohana.sakuya.yoshun.controller.quadra

import net.konohana.sakuya.yoshun.dtos.quadra.Quadra20Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra20FrontendDto
import net.konohana.sakuya.yoshun.services.quadra.Quadra20Service

class Quadra20Controller(
    private val quadra20Service: Quadra20Service
) {
    suspend fun getQuadra20StaList(): List<Quadra20Dto> {
        return quadra20Service.getQuadra20()
    }

    suspend fun getQuadra20StaListByStaCode(staCode: String): Quadra20Dto? {
        return quadra20Service.getQuadra20ByStaCode(staCode = staCode)
    }

    suspend fun getQuadra20FrontendList(): List<Quadra20FrontendDto> {
        // サービス層の新しいメソッドを呼び出す
        return quadra20Service.getQuadra20Frontend()
    }
}
