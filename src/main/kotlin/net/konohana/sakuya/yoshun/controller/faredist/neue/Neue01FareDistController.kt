package net.konohana.sakuya.yoshun.controller.faredist.neue

import net.konohana.sakuya.yoshun.dtos.faredist.neue.Neue01FareDistDataDto
import net.konohana.sakuya.yoshun.services.faredist.neue.Neue01FareDistService

class Neue01FareDistController (
    private val neue01FareDistService: Neue01FareDistService
) {
    suspend fun getNeue01FareDistList(): List<Neue01FareDistDataDto> {
        return neue01FareDistService.getNeue01FareDist()
    }
}
