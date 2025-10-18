package net.konohana.sakuya.yoshun.controller.loc

import net.konohana.sakuya.yoshun.dtos.loc.Loc01Dto
import net.konohana.sakuya.yoshun.services.loc.Loc01Service

class Loc01Controller(
    private val loc01Service: Loc01Service
) {
    suspend fun getLoc01StaList(): List<Loc01Dto> {
        return loc01Service.getLoc01()
    }

    suspend fun getLoc01StaListByStaCode(staCode: String): Loc01Dto? {
        return loc01Service.getLoc01ByStaCode(staCode = staCode)
    }
}
