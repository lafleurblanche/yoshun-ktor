package net.konohana.sakuya.yoshun.controller.third

import net.konohana.sakuya.yoshun.dtos.third.Third09FrontendDto
import net.konohana.sakuya.yoshun.dtos.third.Third09Dto
import net.konohana.sakuya.yoshun.services.third.Third09Service

class Third09Controller(
    private val third09Service: Third09Service
) {
    suspend fun getThird09StaList(): List<Third09Dto> {
        return third09Service.getThird09()
    }

    suspend fun getThird09StaListByStaCode(staCode: String): Third09Dto? {
        return third09Service.getThird09ByStaCode(staCode = staCode)
    }

    suspend fun getThird09FrontendList(): List<Third09FrontendDto> {
        // サービス層の新しいメソッドを呼び出す
        return third09Service.getThird09Frontend()
    }
}
