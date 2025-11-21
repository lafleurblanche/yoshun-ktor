package net.konohana.sakuya.yoshun.controller.quadra

import net.konohana.sakuya.yoshun.dtos.quadra.Quadra23Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra23FrontendDto
import net.konohana.sakuya.yoshun.services.quadra.Quadra23Service

class Quadra23Controller(
    private val quadra23Service: Quadra23Service
) {
    suspend fun getQuadra23StaList(): List<Quadra23Dto> {
        return quadra23Service.getQuadra23()
    }

    suspend fun getQuadra23StaListByStaCode(staCode: String): Quadra23Dto? {
        return quadra23Service.getQuadra23ByStaCode(staCode = staCode)
    }

    suspend fun getQuadra23FrontendList(): List<Quadra23FrontendDto> {
        // サービス層の新しいメソッドを呼び出す
        return quadra23Service.getQuadra23Frontend()
    }
}
