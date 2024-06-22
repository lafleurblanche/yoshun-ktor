package net.konohana.sakuya.yoshun.controller.neue

import net.konohana.sakuya.yoshun.dtos.neue.Neue06Dto
import net.konohana.sakuya.yoshun.services.neue.Neue06Service

class Neue06Controller(
    private val neue06Service: Neue06Service
) {
    suspend fun getNeue06StaList(): List<Neue06Dto> {
        return neue06Service.getNeue06()
    }

    suspend fun getNeue06StaListByStaCode(staCode: String): Neue06Dto? {
        return neue06Service.getNeue06ByStaCode(staCode = staCode)
    }
}
