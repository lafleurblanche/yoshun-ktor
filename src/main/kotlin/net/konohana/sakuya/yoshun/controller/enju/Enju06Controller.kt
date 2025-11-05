package net.konohana.sakuya.yoshun.controller.enju

import net.konohana.sakuya.yoshun.dtos.enju.Enju06Dto
import net.konohana.sakuya.yoshun.services.enju.Enju06Service

class Enju06Controller(
    private val enju06Service: Enju06Service
) {
    suspend fun getEnju06StaList(): List<Enju06Dto> {
        return enju06Service.getEnju06()
    }
}
