package net.konohana.sakuya.yoshun.controller.neue

import net.konohana.sakuya.yoshun.dtos.neue.Neue02Dto
import net.konohana.sakuya.yoshun.services.neue.Neue02Service

class Neue02Controller(
    private val neue02Service: Neue02Service
) {
    suspend fun getNeue02StaList(): List<Neue02Dto> {
        return neue02Service.getNeue02()
    }

    suspend fun getNeue02StaListByStaCode(staCode: String): Neue02Dto? {
        return neue02Service.getNeue02ByStaCode(staCode = staCode)
    }
}
