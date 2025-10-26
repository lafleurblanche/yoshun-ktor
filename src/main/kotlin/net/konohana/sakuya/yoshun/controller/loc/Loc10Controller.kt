package net.konohana.sakuya.yoshun.controller.loc

import net.konohana.sakuya.yoshun.dtos.loc.Loc10Dto
import net.konohana.sakuya.yoshun.services.loc.Loc10Service

class Loc10Controller(
    private val loc10Service: Loc10Service
) {
    suspend fun getLoc10StaList(): List<Loc10Dto> {
        return loc10Service.getLoc10()
    }

    suspend fun getLoc10StaListByStaCode(staCode: String): Loc10Dto? {
        return loc10Service.getLoc10ByStaCode(staCode = staCode)
    }
}
