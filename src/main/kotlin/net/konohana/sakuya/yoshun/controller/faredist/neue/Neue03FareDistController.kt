package net.konohana.sakuya.yoshun.controller.faredist.neue

import net.konohana.sakuya.yoshun.dtos.faredist.neue.Neue03FareDistDataDto
import net.konohana.sakuya.yoshun.services.faredist.neue.Neue03FareDistService

class Neue03FareDistController (
    private val neue03FareDistService: Neue03FareDistService
) {
    suspend fun getNeue03FareDistList(): List<Neue03FareDistDataDto> {
        return neue03FareDistService.getNeue03FareDist()
    }
}
