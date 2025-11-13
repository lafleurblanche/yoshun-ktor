package net.konohana.sakuya.yoshun.dtos.next

import kotlinx.serialization.Serializable

@Serializable
data class Next01FrontendDto(
    val id: Int,
    val viaRouteName: String,
    val staCode: String,
    val staName1: String,
    val staName2: String,
)
