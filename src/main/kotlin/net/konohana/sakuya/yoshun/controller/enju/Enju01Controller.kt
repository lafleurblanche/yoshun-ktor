package net.konohana.sakuya.yoshun.controller.enju

import net.konohana.sakuya.yoshun.dtos.enju.Enju01Dto
import net.konohana.sakuya.yoshun.services.enju.Enju01Service

class Enju01Controller(
    private val enju01Service: Enju01Service
) {
    suspend fun getEnju01StaList(): List<Enju01Dto> {
        return enju01Service.getEnju01()
    }

    suspend fun getEnju01StaListByStaCode(staCode: String): Enju01Dto? {
        return enju01Service.getEnju01ByStaCode(staCode = staCode)
    }
}
