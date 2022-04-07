package com.emissa.apps.poetries.model


import com.google.gson.annotations.SerializedName

data class Poem(
    @SerializedName("author")
    val author: String,
    @SerializedName("linecount")
    val linecount: Int,
    @SerializedName("lines")
    val lines: List<String>,
    @SerializedName("title")
    val title: String
)