package net.konohana.sakuya.yoshun.controller.hnki

import net.konohana.sakuya.yoshun.dtos.hnki.Hnki01Dto
import net.konohana.sakuya.yoshun.services.hnki.Hnki01Service

class Hnki01Controller(
    private val hnki01Service: Hnki01Service
) {
    suspend fun getHnki01StaList(): List<Hnki01Dto> {
        return hnki01Service.getHnki01()
    }

    suspend fun getHnki01StaListByStaCode(staCode: String): Hnki01Dto? {
        return hnki01Service.getHnki01ByStaCode(staCode = staCode)
    }
}
