package com.nusantarian.moviecatalogue.di

import android.content.Context
import androidx.room.Room
import com.nusantarian.moviecatalogue.core.source.local.dao.FavMovieDao
import com.nusantarian.moviecatalogue.core.source.local.database.AppDatabase
import com.nusantarian.moviecatalogue.core.source.local.database.Constant
import com.nusantarian.moviecatalogue.core.source.local.database.LocalPrefManager
import com.nusantarian.moviecatalogue.core.source.remote.network.ApiService
import com.nusantarian.moviecatalogue.core.source.remote.network.AuthInterceptor
import com.nusantarian.moviecatalogue.core.source.remote.network.Routing
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

fun provideRoomDatabase(context: Context): AppDatabase {
    var database: AppDatabase? = null
    database = Room.databaseBuilder(context, AppDatabase::class.java, Constant.MAIN_DB).build()
    return database
}

fun provideFavMovieDao(appDatabase: AppDatabase): FavMovieDao = appDatabase.favMovieDao()

val localModule = module {
    singleOf(::LocalPrefManager)
}

val databaseModule = module {
    singleOf(::provideRoomDatabase)
    singleOf(::provideFavMovieDao)
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
            .addInterceptor(AuthInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder().baseUrl(Routing.MAIN_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val appModule = module{
    includes(localModule, databaseModule, networkModule)
}