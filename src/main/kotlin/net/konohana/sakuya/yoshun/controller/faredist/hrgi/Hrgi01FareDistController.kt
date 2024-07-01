package net.konohana.sakuya.yoshun.controller.faredist.hrgi

import net.konohana.sakuya.yoshun.dtos.faredist.hrgi.Hrgi01FareDistDataDto
import net.konohana.sakuya.yoshun.services.faredist.hrgi.Hrgi01FareDistService

class Hrgi01FareDistController (
    private val neue01FareDistService: Hrgi01FareDistService
) {
    suspend fun getHrgi01FareDistList(): List<Hrgi01FareDistDataDto> {
        return neue01FareDistService.getHrgi01FareDist()
    }
}
