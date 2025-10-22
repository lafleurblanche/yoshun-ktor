package net.konohana.sakuya.yoshun.controller.loc

import net.konohana.sakuya.yoshun.dtos.loc.Loc04Dto
import net.konohana.sakuya.yoshun.services.loc.Loc04Service

class Loc04Controller(
    private val loc04Service: Loc04Service
) {
    suspend fun getLoc04StaList(): List<Loc04Dto> {
        return loc04Service.getLoc04()
    }

    suspend fun getLoc04StaListByStaCode(staCode: String): Loc04Dto? {
        return loc04Service.getLoc04ByStaCode(staCode = staCode)
    }
}
