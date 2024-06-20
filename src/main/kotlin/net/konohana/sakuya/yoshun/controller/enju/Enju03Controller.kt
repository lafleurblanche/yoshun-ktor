package net.konohana.sakuya.yoshun.controller.enju

import net.konohana.sakuya.yoshun.dtos.enju.Enju03Dto
import net.konohana.sakuya.yoshun.services.enju.Enju03Service

class Enju03Controller(
    private val enju03Service: Enju03Service
) {
    suspend fun getEnju03StaList(): List<Enju03Dto> {
        return enju03Service.getEnju03()
    }
}
