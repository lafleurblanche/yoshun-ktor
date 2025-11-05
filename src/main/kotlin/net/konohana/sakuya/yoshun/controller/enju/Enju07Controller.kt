package net.konohana.sakuya.yoshun.controller.enju

import net.konohana.sakuya.yoshun.dtos.enju.Enju07Dto
import net.konohana.sakuya.yoshun.services.enju.Enju07Service

class Enju07Controller(
    private val enju07Service: Enju07Service
) {
    suspend fun getEnju07StaList(): List<Enju07Dto> {
        return enju07Service.getEnju07()
    }
}
