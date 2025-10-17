package net.konohana.sakuya.yoshun.controller.hrgi

import net.konohana.sakuya.yoshun.dtos.hrgi.Hrgi02Dto
import net.konohana.sakuya.yoshun.services.hrgi.Hrgi02Service

class Hrgi02Controller(
    private val hrgi02Service: Hrgi02Service
) {
    suspend fun getHrgi02StaList(): List<Hrgi02Dto> {
        return hrgi02Service.getHrgi02()
    }

    suspend fun getHrgi02StaListByStaCode(staCode: String): Hrgi02Dto? {
        return hrgi02Service.getHrgi02ByStaCode(staCode = staCode)
    }
}
