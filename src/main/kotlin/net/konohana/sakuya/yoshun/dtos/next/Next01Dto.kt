package net.konohana.sakuya.yoshun.dtos.next

import kotlinx.serialization.Serializable

@Serializable
data class Next01Dto(
    val id: Int,
    val routeID: String,
    val staCode: String,
    val fromStaCode: String,
    val toStaCode: String,
    val staName: String,
)
