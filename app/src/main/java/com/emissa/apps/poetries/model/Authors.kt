package com.emissa.apps.poetries.model


import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Authors(
    @SerializedName("authors")
    val authors: List<String?>
)