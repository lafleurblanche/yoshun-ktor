package net.konohana.sakuya.yoshun.dtos.lilie

import kotlinx.serialization.Serializable

@Serializable
data class Lilie01Dto(
    val id: Int,
    val routeID: String,
    val staCode: String,
    val fromStaCode: String,
    val toStaCode: String,
    val staName: String,
)
