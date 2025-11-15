package net.konohana.sakuya.yoshun.controller.third

import net.konohana.sakuya.yoshun.dtos.third.Third05FrontendDto
import net.konohana.sakuya.yoshun.dtos.third.Third05Dto
import net.konohana.sakuya.yoshun.services.third.Third05Service

class Third05Controller(
    private val third05Service: Third05Service
) {
    suspend fun getThird05StaList(): List<Third05Dto> {
        return third05Service.getThird05()
    }

    suspend fun getThird05StaListByStaCode(staCode: String): Third05Dto? {
        return third05Service.getThird05ByStaCode(staCode = staCode)
    }

    suspend fun getThird05FrontendList(): List<Third05FrontendDto> {
        // サービス層の新しいメソッドを呼び出す
        return third05Service.getThird05Frontend()
    }
}
