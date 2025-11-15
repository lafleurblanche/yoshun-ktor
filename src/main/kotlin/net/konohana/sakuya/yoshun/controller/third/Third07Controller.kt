package net.konohana.sakuya.yoshun.controller.third

import net.konohana.sakuya.yoshun.dtos.third.Third07FrontendDto
import net.konohana.sakuya.yoshun.dtos.third.Third07Dto
import net.konohana.sakuya.yoshun.services.third.Third07Service

class Third07Controller(
    private val third07Service: Third07Service
) {
    suspend fun getThird07StaList(): List<Third07Dto> {
        return third07Service.getThird07()
    }

    suspend fun getThird07StaListByStaCode(staCode: String): Third07Dto? {
        return third07Service.getThird07ByStaCode(staCode = staCode)
    }

    suspend fun getThird07FrontendList(): List<Third07FrontendDto> {
        // サービス層の新しいメソッドを呼び出す
        return third07Service.getThird07Frontend()
    }
}
