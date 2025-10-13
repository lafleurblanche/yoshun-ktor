package net.konohana.sakuya.yoshun.dtos.hnki

import kotlinx.serialization.Serializable

@Serializable
data class Hnki02Dto(
    val id: Int,
    val routeID: String,
    val staCode: String,
    val fromStaCode: String,
    val toStaCode: String,
    val staName: String,
)
