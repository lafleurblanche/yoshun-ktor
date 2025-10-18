package net.konohana.sakuya.yoshun.controller.locb

import net.konohana.sakuya.yoshun.dtos.locb.LocB03Dto
import net.konohana.sakuya.yoshun.services.locb.LocB03Service

class LocB03Controller(
    private val locB03Service: LocB03Service
) {
    suspend fun getLocB03StaList(): List<LocB03Dto> {
        return locB03Service.getLocB03()
    }

    suspend fun getLocB03StaListByStaCode(staCode: String): LocB03Dto? {
        return locB03Service.getLocB03ByStaCode(staCode = staCode)
    }
}
