package net.konohana.sakuya.yoshun.controller.enju

import net.konohana.sakuya.yoshun.dtos.enju.Enju12Dto
import net.konohana.sakuya.yoshun.services.enju.Enju12Service

class Enju12Controller(
    private val enju12Service: Enju12Service
) {
    suspend fun getEnju12StaList(): List<Enju12Dto> {
        return enju12Service.getEnju12()
    }

    suspend fun getEnju12StaListByStaCode(staCode: String): Enju12Dto? {
        return enju12Service.getEnju12ByStaCode(staCode = staCode)
    }
}
