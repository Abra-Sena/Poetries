package com.emissa.apps.poetries.model


import com.google.gson.annotations.SerializedName


data class PoemLines(
    @SerializedName("lines")
    val lines: List<String>
)