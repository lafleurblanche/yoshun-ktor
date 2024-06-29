package net.konohana.sakuya.yoshun.dtos.faredist.hrgi

import kotlinx.serialization.Serializable

@Serializable
data class Hrgi01FareDistDataDto (
    val id: String,
    val routeID: String,
    val staCode: String,
    val staName: String,
    val strtPtStaCode: String,
    val distance: Double,
)
