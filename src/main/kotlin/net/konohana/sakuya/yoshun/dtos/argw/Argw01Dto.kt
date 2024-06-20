package net.konohana.sakuya.yoshun.dtos.argw

import kotlinx.serialization.Serializable

@Serializable
data class Argw01Dto(
    val id: Int,
    val routeID: String,
    val staCode: String,
    val fromStaCode: String,
    val toStaCode: String,
    val staName: String,
)
