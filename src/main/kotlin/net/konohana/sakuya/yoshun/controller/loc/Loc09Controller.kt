package net.konohana.sakuya.yoshun.controller.loc

import net.konohana.sakuya.yoshun.dtos.loc.Loc09Dto
import net.konohana.sakuya.yoshun.services.loc.Loc09Service

class Loc09Controller(
    private val loc09Service: Loc09Service
) {
    suspend fun getLoc09StaList(): List<Loc09Dto> {
        return loc09Service.getLoc09()
    }

    suspend fun getLoc09StaListByStaCode(staCode: String): Loc09Dto? {
        return loc09Service.getLoc09ByStaCode(staCode = staCode)
    }
}
