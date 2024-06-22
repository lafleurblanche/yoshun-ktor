package net.konohana.sakuya.yoshun.dtos.faredist.neue

import kotlinx.serialization.Serializable

@Serializable
data class Neue01FareDistDataDto (
    val id: String,
    val routeID: String,
    val staCode: String,
    val staName: String,
    val strtPtStaCode: String,
    val distance: Double,
)
