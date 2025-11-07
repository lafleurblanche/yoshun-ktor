package net.konohana.sakuya.yoshun.controller.enju

import net.konohana.sakuya.yoshun.dtos.enju.Enju18Dto
import net.konohana.sakuya.yoshun.services.enju.Enju18Service

class Enju18Controller(
    private val enju18Service: Enju18Service
) {
    suspend fun getEnju18StaList(): List<Enju18Dto> {
        return enju18Service.getEnju18()
    }

    suspend fun getEnju18StaListByStaCode(staCode: String): Enju18Dto? {
        return enju18Service.getEnju18ByStaCode(staCode = staCode)
    }
}
