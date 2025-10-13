package net.konohana.sakuya.yoshun.dtos.locb

import kotlinx.serialization.Serializable

@Serializable
data class LocB04Dto(
    val id: Int,
    val routeID: String,
    val staCode: String,
    val fromStaCode: String,
    val toStaCode: String,
    val staName: String,
)
