package net.konohana.sakuya.yoshun.controller.hnki

import net.konohana.sakuya.yoshun.dtos.hnki.Hnki02Dto
import net.konohana.sakuya.yoshun.services.hnki.Hnki02Service

class Hnki02Controller(
    private val hnki02Service: Hnki02Service
) {
    suspend fun getHnki02StaList(): List<Hnki02Dto> {
        return hnki02Service.getHnki02()
    }

    suspend fun getHnki02StaListByStaCode(staCode: String): Hnki02Dto? {
        return hnki02Service.getHnki02ByStaCode(staCode = staCode)
    }
}
