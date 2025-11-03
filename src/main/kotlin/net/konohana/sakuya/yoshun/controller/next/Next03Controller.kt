package net.konohana.sakuya.yoshun.controller.next

import net.konohana.sakuya.yoshun.dtos.next.Next03Dto
import net.konohana.sakuya.yoshun.services.next.Next03Service

class Next03Controller(
    private val next03Service: Next03Service
) {
    suspend fun getNext03StaList(): List<Next03Dto> {
        return next03Service.getNext03()
    }

    suspend fun getNext03StaListByStaCode(staCode: String): Next03Dto? {
        return next03Service.getNext03ByStaCode(staCode = staCode)
    }
}
