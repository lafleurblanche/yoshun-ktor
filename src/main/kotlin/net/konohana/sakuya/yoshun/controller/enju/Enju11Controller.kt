package net.konohana.sakuya.yoshun.controller.enju

import net.konohana.sakuya.yoshun.dtos.enju.Enju11Dto
import net.konohana.sakuya.yoshun.services.enju.Enju11Service

class Enju11Controller(
    private val enju11Service: Enju11Service
) {
    suspend fun getEnju11StaList(): List<Enju11Dto> {
        return enju11Service.getEnju11()
    }

    suspend fun getEnju11StaListByStaCode(staCode: String): Enju11Dto? {
        return enju11Service.getEnju11ByStaCode(staCode = staCode)
    }
}
