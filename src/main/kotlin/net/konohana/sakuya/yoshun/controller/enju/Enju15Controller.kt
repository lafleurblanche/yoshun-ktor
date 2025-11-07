package net.konohana.sakuya.yoshun.controller.enju

import net.konohana.sakuya.yoshun.dtos.enju.Enju15Dto
import net.konohana.sakuya.yoshun.services.enju.Enju15Service

class Enju15Controller(
    private val enju15Service: Enju15Service
) {
    suspend fun getEnju15StaList(): List<Enju15Dto> {
        return enju15Service.getEnju15()
    }

    suspend fun getEnju15StaListByStaCode(staCode: String): Enju15Dto? {
        return enju15Service.getEnju15ByStaCode(staCode = staCode)
    }
}
