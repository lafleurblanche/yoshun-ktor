package net.konohana.sakuya.yoshun.controller.enju

import net.konohana.sakuya.yoshun.dtos.enju.Enju19Dto
import net.konohana.sakuya.yoshun.services.enju.Enju19Service

class Enju19Controller(
    private val enju19Service: Enju19Service
) {
    suspend fun getEnju19StaList(): List<Enju19Dto> {
        return enju19Service.getEnju19()
    }

    suspend fun getEnju19StaListByStaCode(staCode: String): Enju19Dto? {
        return enju19Service.getEnju19ByStaCode(staCode = staCode)
    }
}
