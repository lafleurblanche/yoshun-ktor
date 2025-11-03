package net.konohana.sakuya.yoshun.controller.next

import net.konohana.sakuya.yoshun.dtos.next.Next04Dto
import net.konohana.sakuya.yoshun.services.next.Next04Service

class Next04Controller(
    private val next04Service: Next04Service
) {
    suspend fun getNext04StaList(): List<Next04Dto> {
        return next04Service.getNext04()
    }

    suspend fun getNext04StaListByStaCode(staCode: String): Next04Dto? {
        return next04Service.getNext04ByStaCode(staCode = staCode)
    }
}
