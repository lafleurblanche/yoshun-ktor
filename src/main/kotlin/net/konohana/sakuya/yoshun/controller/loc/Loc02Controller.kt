package net.konohana.sakuya.yoshun.controller.loc

import net.konohana.sakuya.yoshun.dtos.loc.Loc02Dto
import net.konohana.sakuya.yoshun.services.loc.Loc02Service

class Loc02Controller(
    private val loc02Service: Loc02Service
) {
    suspend fun getLoc02StaList(): List<Loc02Dto> {
        return loc02Service.getLoc02()
    }

    suspend fun getLoc02StaListByStaCode(staCode: String): Loc02Dto? {
        return loc02Service.getLoc02ByStaCode(staCode = staCode)
    }
}
