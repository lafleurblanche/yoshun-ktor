package net.konohana.sakuya.yoshun.controller.third

import net.konohana.sakuya.yoshun.dtos.third.Third01Dto
import net.konohana.sakuya.yoshun.services.third.Third01Service

class Third01Controller(
    private val third01Service: Third01Service
) {
    suspend fun getThird01StaList(): List<Third01Dto> {
        return third01Service.getThird01()
    }

    suspend fun getThird01StaListByStaCode(staCode: String): Third01Dto? {
        return third01Service.getThird01ByStaCode(staCode = staCode)
    }
}
