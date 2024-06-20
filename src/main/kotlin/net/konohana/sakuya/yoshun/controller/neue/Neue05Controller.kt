package net.konohana.sakuya.yoshun.controller.neue

import net.konohana.sakuya.yoshun.dtos.neue.Neue05Dto
import net.konohana.sakuya.yoshun.services.neue.Neue05Service

class Neue05Controller(
    private val neue05Service: Neue05Service
) {
    suspend fun getNeue05StaList(): List<Neue05Dto> {
        return neue05Service.getNeue05()
    }
}
