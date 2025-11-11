package net.konohana.sakuya.yoshun.dtos.quadra

import kotlinx.serialization.Serializable

@Serializable
data class Quadra08FrontendDto(
    val id: Int,
    val viaRouteName: String,
    val staCode: String,
    val staName1: String,
    val staName2: String,
)
