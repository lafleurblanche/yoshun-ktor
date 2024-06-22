package net.konohana.sakuya.yoshun.controller.neue

import net.konohana.sakuya.yoshun.dtos.neue.Neue08Dto
import net.konohana.sakuya.yoshun.services.neue.Neue08Service

class Neue08Controller(
    private val neue08Service: Neue08Service
) {
    suspend fun getNeue08StaList(): List<Neue08Dto> {
        return neue08Service.getNeue08()
    }

    suspend fun getNeue08StaListByStaCode(staCode: String): Neue08Dto? {
        return neue08Service.getNeue08ByStaCode(staCode = staCode)
    }
}
