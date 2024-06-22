package net.konohana.sakuya.yoshun.controller.lilie

import net.konohana.sakuya.yoshun.dtos.lilie.Lilie04Dto
import net.konohana.sakuya.yoshun.services.lilie.Lilie04Service

class Lilie04Controller(
    private val lilie04Service: Lilie04Service
) {
    suspend fun getLilie04StaList(): List<Lilie04Dto> {
        return lilie04Service.getLilie04()
    }
}
