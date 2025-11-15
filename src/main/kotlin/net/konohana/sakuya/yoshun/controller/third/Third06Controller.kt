package net.konohana.sakuya.yoshun.controller.third

import net.konohana.sakuya.yoshun.dtos.third.Third06FrontendDto
import net.konohana.sakuya.yoshun.dtos.third.Third06Dto
import net.konohana.sakuya.yoshun.services.third.Third06Service

class Third06Controller(
    private val third06Service: Third06Service
) {
    suspend fun getThird06StaList(): List<Third06Dto> {
        return third06Service.getThird06()
    }

    suspend fun getThird06StaListByStaCode(staCode: String): Third06Dto? {
        return third06Service.getThird06ByStaCode(staCode = staCode)
    }

    suspend fun getThird06FrontendList(): List<Third06FrontendDto> {
        // サービス層の新しいメソッドを呼び出す
        return third06Service.getThird06Frontend()
    }
}
