package net.konohana.sakuya.yoshun.controller.enju

import net.konohana.sakuya.yoshun.dtos.enju.Enju08Dto
import net.konohana.sakuya.yoshun.services.enju.Enju08Service

class Enju08Controller(
    private val enju08Service: Enju08Service
) {
    suspend fun getEnju08StaList(): List<Enju08Dto> {
        return enju08Service.getEnju08()
    }
}
