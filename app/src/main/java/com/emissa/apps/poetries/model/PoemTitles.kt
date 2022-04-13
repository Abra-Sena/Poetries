package com.emissa.apps.poetries.model


import androidx.room.Entity
import com.google.gson.annotations.SerializedName


data class PoemTitles(
    @SerializedName("titles")
    val titles: List<String>
)