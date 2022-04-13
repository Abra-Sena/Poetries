package com.emissa.apps.poetries.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.emissa.apps.poetries.utils.DataConverter
import com.google.gson.annotations.SerializedName


@Entity
data class Poem(
    @PrimaryKey
    @SerializedName("title")
    val title: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("linecount")
    val linecount: String,
    @TypeConverters(DataConverter::class)
    @SerializedName("lines")
    val lines: List<String>?
)