package net.konohana.sakuya.yoshun.controller.third

import net.konohana.sakuya.yoshun.dtos.third.Third10Dto
import net.konohana.sakuya.yoshun.services.third.Third10Service

class Third10Controller(
    private val third10Service: Third10Service
) {
    suspend fun getThird10StaList(): List<Third10Dto> {
        return third10Service.getThird10()
    }

    suspend fun getThird10StaListByStaCode(staCode: String): Third10Dto? {
        return third10Service.getThird10ByStaCode(staCode = staCode)
    }
}
