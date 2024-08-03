package net.konohana.sakuya.yoshun.controller.argw

import net.konohana.sakuya.yoshun.dtos.argw.Argw01Dto
import net.konohana.sakuya.yoshun.services.argw.Argw01Service

class Argw01Controller(
    private val argw01Service: Argw01Service
) {
    suspend fun getArgw01StaList(): List<Argw01Dto> {
        return argw01Service.getArgw01()
    }

    suspend fun getArgw01StaListByStaCode(staCode: String): Argw01Dto? {
        return argw01Service.getArgw01ByStaCode(staCode = staCode)
    }
}
