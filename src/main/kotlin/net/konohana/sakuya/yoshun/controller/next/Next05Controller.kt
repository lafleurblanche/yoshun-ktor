package net.konohana.sakuya.yoshun.controller.next

import net.konohana.sakuya.yoshun.dtos.next.Next05FrontendDto
import net.konohana.sakuya.yoshun.dtos.next.Next05Dto
import net.konohana.sakuya.yoshun.services.next.Next05Service

class Next05Controller(
    private val next05Service: Next05Service
) {
    suspend fun getNext05StaList(): List<Next05Dto> {
        return next05Service.getNext05()
    }

    suspend fun getNext05StaListByStaCode(staCode: String): Next05Dto? {
        return next05Service.getNext05ByStaCode(staCode = staCode)
    }

    suspend fun getNext05FrontendList(): List<Next05FrontendDto> {
        // サービス層の新しいメソッドを呼び出す
        return next05Service.getNext05Frontend()
    }
}
