package com.emissa.apps.poetries.model


import com.google.gson.annotations.SerializedName

data class Authors(
    @SerializedName("authors")
    val authors: List<String>
)