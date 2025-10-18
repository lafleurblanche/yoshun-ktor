package net.konohana.sakuya.yoshun.controller.hnki

import net.konohana.sakuya.yoshun.dtos.hnki.Hnki03Dto
import net.konohana.sakuya.yoshun.services.hnki.Hnki03Service

class Hnki03Controller(
    private val hnki03Service: Hnki03Service
) {
    suspend fun getHnki03StaList(): List<Hnki03Dto> {
        return hnki03Service.getHnki03()
    }

    suspend fun getHnki03StaListByStaCode(staCode: String): Hnki03Dto? {
        return hnki03Service.getHnki03ByStaCode(staCode = staCode)
    }
}
