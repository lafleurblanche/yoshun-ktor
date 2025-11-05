package net.konohana.sakuya.yoshun.controller.enju

import net.konohana.sakuya.yoshun.dtos.enju.Enju05Dto
import net.konohana.sakuya.yoshun.services.enju.Enju05Service

class Enju05Controller(
    private val enju05Service: Enju05Service
) {
    suspend fun getEnju05StaList(): List<Enju05Dto> {
        return enju05Service.getEnju05()
    }

    suspend fun getEnju05StaListByStaCode(staCode: String): Enju05Dto? {
        return enju05Service.getEnju05ByStaCode(staCode = staCode)
    }
}
