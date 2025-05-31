package com.nusantarian.moviecatalogue.core.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nusantarian.moviecatalogue.core.source.local.dao.FavMovieDao
import com.nusantarian.moviecatalogue.core.source.local.entity.GenreEntity
import com.nusantarian.moviecatalogue.core.source.local.entity.MovieListEntity

@Database(
    entities = [MovieListEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TypeConvertersHelper::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun favMovieDao(): FavMovieDao
}