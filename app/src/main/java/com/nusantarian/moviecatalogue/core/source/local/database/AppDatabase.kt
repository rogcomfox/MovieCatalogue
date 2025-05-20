package com.nusantarian.moviecatalogue.core.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nusantarian.moviecatalogue.core.source.local.entity.MovieDetailEntity

@Database(
    entities = [MovieDetailEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
}