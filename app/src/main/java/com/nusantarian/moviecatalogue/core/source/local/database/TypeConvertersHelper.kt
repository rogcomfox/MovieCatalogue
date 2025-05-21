package com.nusantarian.moviecatalogue.core.source.local.database

import androidx.room.TypeConverter

class TypeConvertersHelper {
    @TypeConverter
    fun fromListInt(list: List<Int>): String = list.joinToString(",")

    @TypeConverter
    fun toListInt(data: String): List<Int> =
        listOf(*data.split(",").map { it.toInt() }.toTypedArray())
}