package net.konohana.sakuya.yoshun.controller.locb

import net.konohana.sakuya.yoshun.dtos.locb.LocB01Dto
import net.konohana.sakuya.yoshun.services.locb.LocB01Service

class LocB01Controller(
    private val locB01Service: LocB01Service
) {
    suspend fun getLocB01StaList(): List<LocB01Dto> {
        return locB01Service.getLocB01()
    }

    suspend fun getLocB01StaListByStaCode(staCode: String): LocB01Dto? {
        return locB01Service.getLocB01ByStaCode(staCode = staCode)
    }
}
