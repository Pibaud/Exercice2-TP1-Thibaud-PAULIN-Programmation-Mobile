package com.example.trainapp.data.api

import com.google.gson.annotations.SerializedName

data class PlaceResponse(
    @SerializedName("places")
    val places: List<Place>
)

data class Place(
    val id: String,
    val name: String,
    @SerializedName("embedded_type")
    val type: String,
    val label: String,
    @SerializedName("administrative_region")
    val region: Region?
)

data class Region(
    val id: String,
    val name: String,
    @SerializedName("zip_code")
    val zipCode: String?
)