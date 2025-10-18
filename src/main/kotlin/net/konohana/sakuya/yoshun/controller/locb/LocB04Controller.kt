package net.konohana.sakuya.yoshun.controller.locb

import net.konohana.sakuya.yoshun.dtos.locb.LocB04Dto
import net.konohana.sakuya.yoshun.services.locb.LocB04Service

class LocB04Controller(
    private val locB04Service: LocB04Service
) {
    suspend fun getLocB04StaList(): List<LocB04Dto> {
        return locB04Service.getLocB04()
    }

    suspend fun getLocB04StaListByStaCode(staCode: String): LocB04Dto? {
        return locB04Service.getLocB04ByStaCode(staCode = staCode)
    }
}
