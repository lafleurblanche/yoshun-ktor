package net.konohana.sakuya.yoshun.controller.third

import net.konohana.sakuya.yoshun.dtos.third.Third02Dto
import net.konohana.sakuya.yoshun.services.third.Third02Service

class Third02Controller(
    private val third02Service: Third02Service
) {
    suspend fun getThird02StaList(): List<Third02Dto> {
        return third02Service.getThird02()
    }

    suspend fun getThird02StaListByStaCode(staCode: String): Third02Dto? {
        return third02Service.getThird02ByStaCode(staCode = staCode)
    }
}
