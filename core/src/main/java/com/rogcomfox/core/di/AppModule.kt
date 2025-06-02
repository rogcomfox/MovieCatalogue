package com.rogcomfox.core.di

import com.rogcomfox.core.BuildConfig
import com.rogcomfox.core.repo.MoviesRepo
import com.rogcomfox.core.repo.TvSeriesRepo
import com.rogcomfox.core.source.local.database.LocalPrefManager
import com.rogcomfox.core.source.remote.network.ApiService
import com.rogcomfox.core.source.remote.network.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

//fun provideRoomDatabase(context: Context): AppDatabase {
//    var database: AppDatabase? = null
//    database = Room.databaseBuilder(context, AppDatabase::class.java, Constant.MAIN_DB).build()
//    return database
//}
//
//fun provideFavMovieDao(appDatabase: AppDatabase): FavMovieDao = appDatabase.favMovieDao()

val localModule = module {
    singleOf(::LocalPrefManager)
}

val repoModule = module {
    singleOf(::MoviesRepo)
    singleOf(::TvSeriesRepo)
}

val databaseModule = module {
//    singleOf(::provideRoomDatabase)
//    singleOf(::provideFavMovieDao)
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    if (BuildConfig.DEBUG)
                        HttpLoggingInterceptor.Level.BODY
                    else
                        HttpLoggingInterceptor.Level.NONE
                )
            )
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    if (BuildConfig.DEBUG)
                        HttpLoggingInterceptor.Level.HEADERS
                    else
                        HttpLoggingInterceptor.Level.NONE
                )
            )
            .addInterceptor(AuthInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder().baseUrl(BuildConfig.MAIN_BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val appModule = module{
    includes(localModule, databaseModule, networkModule, repoModule)
}