package net.konohana.sakuya.yoshun.dtos.quadra

import kotlinx.serialization.Serializable

@Serializable
data class Quadra15Dto(
    val id: Int,
    val routeID: String,
    val staCode: String,
    val fromStaCode: String,
    val toStaCode: String,
    val staName: String,
)
