package net.konohana.sakuya.yoshun.controller.neue

import net.konohana.sakuya.yoshun.dtos.neue.Neue01Dto
import net.konohana.sakuya.yoshun.services.neue.Neue01Service

class Neue01Controller(
    private val neue01Service: Neue01Service
) {
    suspend fun getNeue01StaList(): List<Neue01Dto> {
        return neue01Service.getNeue01()
    }

    suspend fun getNeue01StaListByStaCode(staCode: String): Neue01Dto? {
        return neue01Service.getNeue01ByStaCode(staCode = staCode)
    }
}
