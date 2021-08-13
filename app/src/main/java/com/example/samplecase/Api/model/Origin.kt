package com.example.samplecase.Api.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Origin(
    @SerialName("name")
    val name: String?,
    @SerialName("url")
    val url: String?
)