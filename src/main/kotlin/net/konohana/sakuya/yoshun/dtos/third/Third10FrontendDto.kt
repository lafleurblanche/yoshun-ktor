package net.konohana.sakuya.yoshun.dtos.third

import kotlinx.serialization.Serializable

@Serializable
data class Third10FrontendDto(
    val id: Int,
    val viaRouteName: String,
    val staCode: String,
    val staName1: String,
    val staName2: String,
)
