package com.growell.model

import com.google.gson.annotations.SerializedName


data class ImageModelResponse(
    @SerializedName("objects")
    val objects: List<String>
)