package net.konohana.sakuya.yoshun.dtos.gate

import kotlinx.serialization.Serializable

@Serializable
data class Gate05Dto(
    val id: Int,
    val routeID: String,
    val staCode: String,
    val fromStaCode: String,
    val toStaCode: String,
    val staName: String,
)
