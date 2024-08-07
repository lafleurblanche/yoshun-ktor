package net.konohana.sakuya.yoshun.dtos.faredist.argw

import kotlinx.serialization.Serializable

@Serializable
data class Argw01FareDistDataDto (
    val id: String,
    val routeID: String,
    val staCode: String,
    val staName: String,
    val distance: Double,
)
