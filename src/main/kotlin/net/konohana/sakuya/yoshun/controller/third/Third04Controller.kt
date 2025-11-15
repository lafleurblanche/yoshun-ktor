package net.konohana.sakuya.yoshun.controller.third

import net.konohana.sakuya.yoshun.dtos.third.Third04FrontendDto
import net.konohana.sakuya.yoshun.dtos.third.Third04Dto
import net.konohana.sakuya.yoshun.services.third.Third04Service

class Third04Controller(
    private val third04Service: Third04Service
) {
    suspend fun getThird04StaList(): List<Third04Dto> {
        return third04Service.getThird04()
    }

    suspend fun getThird04StaListByStaCode(staCode: String): Third04Dto? {
        return third04Service.getThird04ByStaCode(staCode = staCode)
    }

    suspend fun getThird04FrontendList(): List<Third04FrontendDto> {
        // サービス層の新しいメソッドを呼び出す
        return third04Service.getThird04Frontend()
    }
}
