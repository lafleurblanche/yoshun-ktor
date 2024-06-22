package net.konohana.sakuya.yoshun.controller.neue

import net.konohana.sakuya.yoshun.dtos.neue.Neue03Dto
import net.konohana.sakuya.yoshun.services.neue.Neue03Service

class Neue03Controller(
    private val neue03Service: Neue03Service
) {
    suspend fun getNeue03StaList(): List<Neue03Dto> {
        return neue03Service.getNeue03()
    }

    suspend fun getNeue03StaListByStaCode(staCode: String): Neue03Dto? {
        return neue03Service.getNeue03ByStaCode(staCode = staCode)
    }
}
