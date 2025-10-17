package net.konohana.sakuya.yoshun.controller.hrgi

import net.konohana.sakuya.yoshun.dtos.hrgi.Hrgi01Dto
import net.konohana.sakuya.yoshun.services.hrgi.Hrgi01Service

class Hrgi01Controller(
    private val hrgi01Service: Hrgi01Service
) {
    suspend fun getHrgi01StaList(): List<Hrgi01Dto> {
        return hrgi01Service.getHrgi01()
    }

    suspend fun getHrgi01StaListByStaCode(staCode: String): Hrgi01Dto? {
        return hrgi01Service.getHrgi01ByStaCode(staCode = staCode)
    }
}
