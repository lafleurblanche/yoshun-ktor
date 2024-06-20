package net.konohana.sakuya.yoshun.controller.neue

import net.konohana.sakuya.yoshun.dtos.neue.Neue07Dto
import net.konohana.sakuya.yoshun.services.neue.Neue07Service

class Neue07Controller(
    private val neue07Service: Neue07Service
) {
    suspend fun getNeue07StaList(): List<Neue07Dto> {
        return neue07Service.getNeue07()
    }
}
