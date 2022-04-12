package com.emissa.apps.poetries.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Poem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("author")
    val author: String,
    @SerializedName("linecount")
    val linecount: Int,
    @SerializedName("lines")
    val lines: List<String>,
    @SerializedName("title")
    val title: String
)