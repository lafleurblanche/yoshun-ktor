package net.konohana.sakuya.yoshun.controller.loc

import net.konohana.sakuya.yoshun.dtos.loc.Loc03Dto
import net.konohana.sakuya.yoshun.services.loc.Loc03Service

class Loc03Controller(
    private val loc03Service: Loc03Service
) {
    suspend fun getLoc03StaList(): List<Loc03Dto> {
        return loc03Service.getLoc03()
    }

    suspend fun getLoc03StaListByStaCode(staCode: String): Loc03Dto? {
        return loc03Service.getLoc03ByStaCode(staCode = staCode)
    }
}
