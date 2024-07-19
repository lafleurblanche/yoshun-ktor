package net.konohana.sakuya.yoshun.controller.faredist.neue

import net.konohana.sakuya.yoshun.dtos.faredist.neue.Neue04FareDistDataDto
import net.konohana.sakuya.yoshun.services.faredist.neue.Neue04FareDistService

class Neue04FareDistController (
    private val neue04FareDistService: Neue04FareDistService
) {
    suspend fun getNeue04FareDistList(): List<Neue04FareDistDataDto> {
        return neue04FareDistService.getNeue04FareDist()
    }
}
