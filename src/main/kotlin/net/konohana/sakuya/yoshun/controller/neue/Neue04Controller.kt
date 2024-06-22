package net.konohana.sakuya.yoshun.controller.neue

import net.konohana.sakuya.yoshun.dtos.neue.Neue04Dto
import net.konohana.sakuya.yoshun.services.neue.Neue04Service

class Neue04Controller(
    private val neue04Service: Neue04Service
) {
    suspend fun getNeue04StaList(): List<Neue04Dto> {
        return neue04Service.getNeue04()
    }

    suspend fun getNeue04StaListByStaCode(staCode: String): Neue04Dto? {
        return neue04Service.getNeue04ByStaCode(staCode = staCode)
    }
}
