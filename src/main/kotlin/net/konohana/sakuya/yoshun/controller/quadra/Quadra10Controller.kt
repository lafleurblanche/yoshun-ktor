package net.konohana.sakuya.yoshun.controller.quadra

import net.konohana.sakuya.yoshun.dtos.quadra.Quadra10Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra10FrontendDto
import net.konohana.sakuya.yoshun.services.quadra.Quadra10Service

class Quadra10Controller(
    private val quadra10Service: Quadra10Service
) {
    suspend fun getQuadra10StaList(): List<Quadra10Dto> {
        return quadra10Service.getQuadra10()
    }

    suspend fun getQuadra10StaListByStaCode(staCode: String): Quadra10Dto? {
        return quadra10Service.getQuadra10ByStaCode(staCode = staCode)
    }

    suspend fun getQuadra10FrontendList(): List<Quadra10FrontendDto> {
        // サービス層の新しいメソッドを呼び出す
        return quadra10Service.getQuadra10Frontend()
    }
}
