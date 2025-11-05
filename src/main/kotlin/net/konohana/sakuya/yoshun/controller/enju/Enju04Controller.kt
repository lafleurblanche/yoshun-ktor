package net.konohana.sakuya.yoshun.controller.enju

import net.konohana.sakuya.yoshun.dtos.enju.Enju04Dto
import net.konohana.sakuya.yoshun.services.enju.Enju04Service

class Enju04Controller(
    private val enju04Service: Enju04Service
) {
    suspend fun getEnju04StaList(): List<Enju04Dto> {
        return enju04Service.getEnju04()
    }

    suspend fun getEnju04StaListByStaCode(staCode: String): Enju04Dto? {
        return enju04Service.getEnju04ByStaCode(staCode = staCode)
    }
}
