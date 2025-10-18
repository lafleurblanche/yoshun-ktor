package net.konohana.sakuya.yoshun.controller.hnki

import net.konohana.sakuya.yoshun.dtos.hnki.Hnki04Dto
import net.konohana.sakuya.yoshun.services.hnki.Hnki04Service

class Hnki04Controller(
    private val hnki04Service: Hnki04Service
) {
    suspend fun getHnki04StaList(): List<Hnki04Dto> {
        return hnki04Service.getHnki04()
    }

    suspend fun getHnki04StaListByStaCode(staCode: String): Hnki04Dto? {
        return hnki04Service.getHnki04ByStaCode(staCode = staCode)
    }
}
