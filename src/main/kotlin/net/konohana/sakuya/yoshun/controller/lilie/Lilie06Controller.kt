package net.konohana.sakuya.yoshun.controller.lilie

import net.konohana.sakuya.yoshun.dtos.lilie.Lilie06Dto
import net.konohana.sakuya.yoshun.services.lilie.Lilie06Service

class Lilie06Controller(
    private val lilie06Service: Lilie06Service
) {
    suspend fun getLilie06StaList(): List<Lilie06Dto> {
        return lilie06Service.getLilie06()
    }
}
