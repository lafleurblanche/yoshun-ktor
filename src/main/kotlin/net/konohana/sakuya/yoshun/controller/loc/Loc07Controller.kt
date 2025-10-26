package net.konohana.sakuya.yoshun.controller.loc

import net.konohana.sakuya.yoshun.dtos.loc.Loc07Dto
import net.konohana.sakuya.yoshun.services.loc.Loc07Service

class Loc07Controller(
    private val loc07Service: Loc07Service
) {
    suspend fun getLoc07StaList(): List<Loc07Dto> {
        return loc07Service.getLoc07()
    }

    suspend fun getLoc07StaListByStaCode(staCode: String): Loc07Dto? {
        return loc07Service.getLoc07ByStaCode(staCode = staCode)
    }
}
