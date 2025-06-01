package com.rogcomfox.core.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rogcomfox.core.source.local.database.Constant
import com.rogcomfox.core.source.local.entity.MovieDetailEntity

@Dao
interface FavMovieDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertFavMovies(moviesData: List<MovieDetailEntity>)

//    @Query("SELECT * FROM ${Constant.FAV_TABLE}")
//    fun getAllFavMovies()

//    @Query("DELETE FROM ${Constant.FAV_TABLE} WHERE id= :movieId")
//    suspend fun deleteAllFavMovies(movieId: String)
}