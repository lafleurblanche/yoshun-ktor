package net.konohana.sakuya.yoshun.dtos.neue

import kotlinx.serialization.Serializable

@Serializable
data class Neue02Dto(
    val id: Int,
    val routeID: String,
    val staCode: String,
    val fromStaCode: String,
    val toStaCode: String,
    val staName: String,
)
