package net.konohana.sakuya.yoshun.controller.enju

import net.konohana.sakuya.yoshun.dtos.enju.Enju13Dto
import net.konohana.sakuya.yoshun.services.enju.Enju13Service

class Enju13Controller(
    private val enju13Service: Enju13Service
) {
    suspend fun getEnju13StaList(): List<Enju13Dto> {
        return enju13Service.getEnju13()
    }

    suspend fun getEnju13StaListByStaCode(staCode: String): Enju13Dto? {
        return enju13Service.getEnju13ByStaCode(staCode = staCode)
    }
}
