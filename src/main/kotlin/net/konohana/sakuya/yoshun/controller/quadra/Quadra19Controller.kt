package net.konohana.sakuya.yoshun.controller.quadra

import net.konohana.sakuya.yoshun.dtos.quadra.Quadra19Dto
import net.konohana.sakuya.yoshun.dtos.quadra.Quadra19FrontendDto
import net.konohana.sakuya.yoshun.services.quadra.Quadra19Service

class Quadra19Controller(
    private val quadra19Service: Quadra19Service
) {
    suspend fun getQuadra19StaList(): List<Quadra19Dto> {
        return quadra19Service.getQuadra19()
    }

    suspend fun getQuadra19StaListByStaCode(staCode: String): Quadra19Dto? {
        return quadra19Service.getQuadra19ByStaCode(staCode = staCode)
    }

    suspend fun getQuadra19FrontendList(): List<Quadra19FrontendDto> {
        // サービス層の新しいメソッドを呼び出す
        return quadra19Service.getQuadra19Frontend()
    }
}
