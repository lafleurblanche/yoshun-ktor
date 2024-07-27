package net.konohana.sakuya.yoshun.controller.lilie

import net.konohana.sakuya.yoshun.dtos.lilie.Lilie07Dto
import net.konohana.sakuya.yoshun.services.lilie.Lilie07Service

class Lilie07Controller(
    private val lilie07Service: Lilie07Service
) {
    suspend fun getLilie07StaList(): List<Lilie07Dto> {
        return lilie07Service.getLilie07()
    }

    suspend fun getLilie07StaListByStaCode(staCode: String): Lilie07Dto? {
        return lilie07Service.getLilie07ByStaCode(staCode = staCode)
    }
}
