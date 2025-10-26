package net.konohana.sakuya.yoshun.controller.loc

import net.konohana.sakuya.yoshun.dtos.loc.Loc11Dto
import net.konohana.sakuya.yoshun.services.loc.Loc11Service

class Loc11Controller(
    private val loc11Service: Loc11Service
) {
    suspend fun getLoc11StaList(): List<Loc11Dto> {
        return loc11Service.getLoc11()
    }

    suspend fun getLoc11StaListByStaCode(staCode: String): Loc11Dto? {
        return loc11Service.getLoc11ByStaCode(staCode = staCode)
    }
}
