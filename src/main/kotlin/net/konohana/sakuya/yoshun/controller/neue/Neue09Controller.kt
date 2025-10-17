package net.konohana.sakuya.yoshun.controller.neue

import net.konohana.sakuya.yoshun.dtos.neue.Neue09Dto
import net.konohana.sakuya.yoshun.services.neue.Neue09Service

class Neue09Controller(
    private val neue09Service: Neue09Service
) {
    suspend fun getNeue09StaList(): List<Neue09Dto> {
        return neue09Service.getNeue09()
    }

    suspend fun getNeue09StaListByStaCode(staCode: String): Neue09Dto? {
        return neue09Service.getNeue09ByStaCode(staCode = staCode)
    }
}
