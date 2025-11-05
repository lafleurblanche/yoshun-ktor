package net.konohana.sakuya.yoshun.controller.enju

import net.konohana.sakuya.yoshun.dtos.enju.Enju02Dto
import net.konohana.sakuya.yoshun.services.enju.Enju02Service

class Enju02Controller(
    private val enju02Service: Enju02Service
) {
    suspend fun getEnju02StaList(): List<Enju02Dto> {
        return enju02Service.getEnju02()
    }

    suspend fun getEnju02StaListByStaCode(staCode: String): Enju02Dto? {
        return enju02Service.getEnju02ByStaCode(staCode = staCode)
    }
}
