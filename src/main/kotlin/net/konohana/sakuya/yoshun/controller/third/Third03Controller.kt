package net.konohana.sakuya.yoshun.controller.third

import net.konohana.sakuya.yoshun.dtos.third.Third03FrontendDto
import net.konohana.sakuya.yoshun.dtos.third.Third03Dto
import net.konohana.sakuya.yoshun.services.third.Third03Service

class Third03Controller(
    private val third03Service: Third03Service
) {
    suspend fun getThird03StaList(): List<Third03Dto> {
        return third03Service.getThird03()
    }

    suspend fun getThird03StaListByStaCode(staCode: String): Third03Dto? {
        return third03Service.getThird03ByStaCode(staCode = staCode)
    }

    suspend fun getThird03FrontendList(): List<Third03FrontendDto> {
        // サービス層の新しいメソッドを呼び出す
        return third03Service.getThird03Frontend()
    }
}
