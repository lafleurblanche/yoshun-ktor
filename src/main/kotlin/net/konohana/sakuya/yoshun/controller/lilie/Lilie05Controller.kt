package net.konohana.sakuya.yoshun.controller.lilie

import net.konohana.sakuya.yoshun.dtos.lilie.Lilie05Dto
import net.konohana.sakuya.yoshun.services.lilie.Lilie05Service

class Lilie05Controller(
    private val lilie05Service: Lilie05Service
) {
    suspend fun getLilie05StaList(): List<Lilie05Dto> {
        return lilie05Service.getLilie05()
    }

    suspend fun getLilie05StaListByStaCode(staCode: String): Lilie05Dto? {
        return lilie05Service.getLilie05ByStaCode(staCode = staCode)
    }
}
