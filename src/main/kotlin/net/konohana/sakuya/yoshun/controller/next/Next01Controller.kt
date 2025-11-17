package net.konohana.sakuya.yoshun.controller.next

import net.konohana.sakuya.yoshun.dtos.next.Next01Dto
import net.konohana.sakuya.yoshun.dtos.next.Next01FrontendDto
import net.konohana.sakuya.yoshun.services.next.Next01Service

class Next01Controller(
    private val next01Service: Next01Service
) {
    suspend fun getNext01StaList(): List<Next01Dto> {
        return next01Service.getNext01()
    }

    suspend fun getNext01StaListByStaCode(staCode: String): Next01Dto? {
        return next01Service.getNext01ByStaCode(staCode = staCode)
    }

    suspend fun getNext01FrontendList(): List<Next01FrontendDto> {
        // サービス層の新しいメソッドを呼び出す
        return next01Service.getNext01Frontend()
    }
}
