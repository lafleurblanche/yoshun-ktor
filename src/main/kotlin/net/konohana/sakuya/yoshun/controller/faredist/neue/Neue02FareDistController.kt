package net.konohana.sakuya.yoshun.controller.faredist.neue

import net.konohana.sakuya.yoshun.dtos.faredist.neue.Neue02FareDistDataDto
import net.konohana.sakuya.yoshun.services.faredist.neue.Neue02FareDistService

class Neue02FareDistController (
    private val neue02FareDistService: Neue02FareDistService
) {
    suspend fun getNeue02FareDistList(): List<Neue02FareDistDataDto> {
        return neue02FareDistService.getNeue02FareDist()
    }
}
