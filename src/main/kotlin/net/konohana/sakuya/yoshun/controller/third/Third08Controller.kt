package net.konohana.sakuya.yoshun.controller.third

import net.konohana.sakuya.yoshun.dtos.third.Third08FrontendDto
import net.konohana.sakuya.yoshun.dtos.third.Third08Dto
import net.konohana.sakuya.yoshun.services.third.Third08Service

class Third08Controller(
    private val third08Service: Third08Service
) {
    suspend fun getThird08StaList(): List<Third08Dto> {
        return third08Service.getThird08()
    }

    suspend fun getThird08StaListByStaCode(staCode: String): Third08Dto? {
        return third08Service.getThird08ByStaCode(staCode = staCode)
    }

    suspend fun getThird08FrontendList(): List<Third08FrontendDto> {
        // サービス層の新しいメソッドを呼び出す
        return third08Service.getThird08Frontend()
    }
}
