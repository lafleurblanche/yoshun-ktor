package net.konohana.sakuya.yoshun.controller.enju

import net.konohana.sakuya.yoshun.dtos.enju.Enju09Dto
import net.konohana.sakuya.yoshun.services.enju.Enju09Service

class Enju09Controller(
    private val enju09Service: Enju09Service
) {
    suspend fun getEnju09StaList(): List<Enju09Dto> {
        return enju09Service.getEnju09()
    }
}
