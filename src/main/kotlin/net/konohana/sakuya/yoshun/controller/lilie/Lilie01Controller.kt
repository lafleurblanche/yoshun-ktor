package net.konohana.sakuya.yoshun.controller.lilie

import net.konohana.sakuya.yoshun.dtos.lilie.Lilie01Dto
import net.konohana.sakuya.yoshun.services.lilie.Lilie01Service

class Lilie01Controller(
    private val lilie01Service: Lilie01Service
) {
    suspend fun getLilie01StaList(): List<Lilie01Dto> {
        return lilie01Service.getLilie01()
    }

    suspend fun getLilie01StaListByStaCode(staCode: String): Lilie01Dto? {
        return lilie01Service.getLilie01ByStaCode(staCode = staCode)
    }
}
