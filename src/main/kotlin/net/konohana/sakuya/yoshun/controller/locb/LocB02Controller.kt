package net.konohana.sakuya.yoshun.controller.locb

import net.konohana.sakuya.yoshun.dtos.locb.LocB02Dto
import net.konohana.sakuya.yoshun.services.locb.LocB02Service

class LocB02Controller(
    private val locB02Service: LocB02Service
) {
    suspend fun getLocB02StaList(): List<LocB02Dto> {
        return locB02Service.getLocB02()
    }

    suspend fun getLocB02StaListByStaCode(staCode: String): LocB02Dto? {
        return locB02Service.getLocB02ByStaCode(staCode = staCode)
    }
}
