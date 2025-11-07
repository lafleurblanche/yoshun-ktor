package net.konohana.sakuya.yoshun.controller.enju

import net.konohana.sakuya.yoshun.dtos.enju.Enju17Dto
import net.konohana.sakuya.yoshun.services.enju.Enju17Service

class Enju17Controller(
    private val enju17Service: Enju17Service
) {
    suspend fun getEnju17StaList(): List<Enju17Dto> {
        return enju17Service.getEnju17()
    }

    suspend fun getEnju17StaListByStaCode(staCode: String): Enju17Dto? {
        return enju17Service.getEnju17ByStaCode(staCode = staCode)
    }
}
