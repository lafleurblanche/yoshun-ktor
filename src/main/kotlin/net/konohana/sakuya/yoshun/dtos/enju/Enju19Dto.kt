package net.konohana.sakuya.yoshun.dtos.enju

import kotlinx.serialization.Serializable

@Serializable
data class Enju19Dto(
    val id: Int,
    val routeID: String,
    val staCode: String,
    val fromStaCode: String,
    val toStaCode: String,
    val staName: String,
)
