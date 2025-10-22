package net.konohana.sakuya.yoshun.controller.loc

import net.konohana.sakuya.yoshun.dtos.loc.Loc05Dto
import net.konohana.sakuya.yoshun.services.loc.Loc05Service

class Loc05Controller(
    private val loc05Service: Loc05Service
) {
    suspend fun getLoc05StaList(): List<Loc05Dto> {
        return loc05Service.getLoc05()
    }

    suspend fun getLoc05StaListByStaCode(staCode: String): Loc05Dto? {
        return loc05Service.getLoc05ByStaCode(staCode = staCode)
    }
}
