package net.konohana.sakuya.yoshun.controller.enju

import net.konohana.sakuya.yoshun.dtos.enju.Enju10Dto
import net.konohana.sakuya.yoshun.services.enju.Enju10Service

class Enju10Controller(
    private val enju10Service: Enju10Service
) {
    suspend fun getEnju10StaList(): List<Enju10Dto> {
        return enju10Service.getEnju10()
    }

    suspend fun getEnju10StaListByStaCode(staCode: String): Enju10Dto? {
        return enju10Service.getEnju10ByStaCode(staCode = staCode)
    }
}
