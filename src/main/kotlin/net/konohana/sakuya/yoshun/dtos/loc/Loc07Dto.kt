package net.konohana.sakuya.yoshun.dtos.loc

import kotlinx.serialization.Serializable

@Serializable
data class Loc07Dto(
    val id: Int,
    val routeID: String,
    val staCode: String,
    val fromStaCode: String,
    val toStaCode: String,
    val staName: String,
)
