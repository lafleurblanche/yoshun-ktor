package net.konohana.sakuya.yoshun.controller.faredist.argw

import net.konohana.sakuya.yoshun.dtos.faredist.argw.Argw01FareDistDataDto
import net.konohana.sakuya.yoshun.services.faredist.argw.Argw01FareDistService

class Argw01FareDistController (
    private val neue01FareDistService: Argw01FareDistService
) {
    suspend fun getArgw01FareDistList(): List<Argw01FareDistDataDto> {
        return neue01FareDistService.getArgw01FareDist()
    }

    suspend fun getArgw01FareDistListByStaCode(staCode: String): Argw01FareDistDataDto? {
        return neue01FareDistService.getArgw01FareDistByStaCode(staCode = staCode)
    }
}
