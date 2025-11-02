package net.konohana.sakuya.yoshun.controller.quadra

import net.konohana.sakuya.yoshun.dtos.quadra.Quadra06Dto
import net.konohana.sakuya.yoshun.services.quadra.Quadra06Service

class Quadra06Controller(
    private val quadra06Service: Quadra06Service
) {
    suspend fun getQuadra06StaList(): List<Quadra06Dto> {
        return quadra06Service.getQuadra06()
    }

    suspend fun getQuadra06StaListByStaCode(staCode: String): Quadra06Dto? {
        return quadra06Service.getQuadra06ByStaCode(staCode = staCode)
    }
}
