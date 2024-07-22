package net.konohana.sakuya.yoshun.controller.lilie

import net.konohana.sakuya.yoshun.dtos.lilie.Lilie02Dto
import net.konohana.sakuya.yoshun.services.lilie.Lilie02Service

class Lilie02Controller(
    private val lilie02Service: Lilie02Service
) {
    suspend fun getLilie02StaList(): List<Lilie02Dto> {
        return lilie02Service.getLilie02()
    }

    suspend fun getLilie02StaListByStaCode(staCode: String): Lilie02Dto? {
        return lilie02Service.getLilie02ByStaCode(staCode = staCode)
    }
}
