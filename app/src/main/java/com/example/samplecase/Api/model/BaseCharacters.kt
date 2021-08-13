package com.example.samplecase.Api.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseCharacters(
    @SerialName("info")
    val info: Minfo?,
    @SerialName("results")
    val results: List<Result>?
)