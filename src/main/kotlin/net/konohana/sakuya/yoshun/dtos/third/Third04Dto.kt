package net.konohana.sakuya.yoshun.dtos.third

import kotlinx.serialization.Serializable

@Serializable
data class Third04Dto(
    val id: Int,
    val routeID: String,
    val staCode: String,
    val fromStaCode: String,
    val toStaCode: String,
    val staName: String,
)
