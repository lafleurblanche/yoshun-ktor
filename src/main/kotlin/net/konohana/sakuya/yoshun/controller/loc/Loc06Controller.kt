package net.konohana.sakuya.yoshun.controller.loc

import net.konohana.sakuya.yoshun.dtos.loc.Loc06Dto
import net.konohana.sakuya.yoshun.services.loc.Loc06Service

class Loc06Controller(
    private val loc06Service: Loc06Service
) {
    suspend fun getLoc06StaList(): List<Loc06Dto> {
        return loc06Service.getLoc06()
    }

    suspend fun getLoc06StaListByStaCode(staCode: String): Loc06Dto? {
        return loc06Service.getLoc06ByStaCode(staCode = staCode)
    }
}
