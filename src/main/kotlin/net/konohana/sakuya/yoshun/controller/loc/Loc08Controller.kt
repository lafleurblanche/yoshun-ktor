package net.konohana.sakuya.yoshun.controller.loc

import net.konohana.sakuya.yoshun.dtos.loc.Loc08Dto
import net.konohana.sakuya.yoshun.services.loc.Loc08Service

class Loc08Controller(
    private val loc08Service: Loc08Service
) {
    suspend fun getLoc08StaList(): List<Loc08Dto> {
        return loc08Service.getLoc08()
    }

    suspend fun getLoc08StaListByStaCode(staCode: String): Loc08Dto? {
        return loc08Service.getLoc08ByStaCode(staCode = staCode)
    }
}
