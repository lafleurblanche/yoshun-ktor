package net.konohana.sakuya.yoshun.controller.enju

import net.konohana.sakuya.yoshun.dtos.enju.Enju16Dto
import net.konohana.sakuya.yoshun.services.enju.Enju16Service

class Enju16Controller(
    private val enju16Service: Enju16Service
) {
    suspend fun getEnju16StaList(): List<Enju16Dto> {
        return enju16Service.getEnju16()
    }

    suspend fun getEnju16StaListByStaCode(staCode: String): Enju16Dto? {
        return enju16Service.getEnju16ByStaCode(staCode = staCode)
    }
}
