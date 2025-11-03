package net.konohana.sakuya.yoshun.controller.next

import net.konohana.sakuya.yoshun.dtos.next.Next02Dto
import net.konohana.sakuya.yoshun.services.next.Next02Service

class Next02Controller(
    private val next02Service: Next02Service
) {
    suspend fun getNext02StaList(): List<Next02Dto> {
        return next02Service.getNext02()
    }

    suspend fun getNext02StaListByStaCode(staCode: String): Next02Dto? {
        return next02Service.getNext02ByStaCode(staCode = staCode)
    }
}
