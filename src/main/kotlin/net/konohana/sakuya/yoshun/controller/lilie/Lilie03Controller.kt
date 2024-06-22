package net.konohana.sakuya.yoshun.controller.lilie

import net.konohana.sakuya.yoshun.dtos.lilie.Lilie03Dto
import net.konohana.sakuya.yoshun.services.lilie.Lilie03Service

class Lilie03Controller(
    private val lilie03Service: Lilie03Service
) {
    suspend fun getLilie03StaList(): List<Lilie03Dto> {
        return lilie03Service.getLilie03()
    }
}
