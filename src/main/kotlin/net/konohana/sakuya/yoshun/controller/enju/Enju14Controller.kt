package net.konohana.sakuya.yoshun.controller.enju

import net.konohana.sakuya.yoshun.dtos.enju.Enju14Dto
import net.konohana.sakuya.yoshun.services.enju.Enju14Service

class Enju14Controller(
    private val enju14Service: Enju14Service
) {
    suspend fun getEnju14StaList(): List<Enju14Dto> {
        return enju14Service.getEnju14()
    }

    suspend fun getEnju14StaListByStaCode(staCode: String): Enju14Dto? {
        return enju14Service.getEnju14ByStaCode(staCode = staCode)
    }
}
