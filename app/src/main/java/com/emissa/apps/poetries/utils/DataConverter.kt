package com.emissa.apps.poetries.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@ProvidedTypeConverter
class DataConverter {
//    companion object {
//        // code from developer android website
//    }
    @TypeConverter
    fun fromStringToList(value: String) : List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }
    @TypeConverter
    fun fromListToString(lines: List<String>) : String {
        return Gson().toJson(lines)
    }
}