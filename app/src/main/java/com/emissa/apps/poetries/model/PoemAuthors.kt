package com.emissa.apps.poetries.model


import androidx.room.Entity
import com.google.gson.annotations.SerializedName


data class PoemAuthors(
    @SerializedName("authors")
    val authors: List<String>
)