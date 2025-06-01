package com.rogcomfox.core.source.remote.network

import com.rogcomfox.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val isAuthHeaderEnable = original.headers["isAuthorize"] == "true"
        val requestBuilder = chain
            .request()
            .newBuilder()
            .removeHeader("isAuthorize")

        if (isAuthHeaderEnable) {
            requestBuilder.addHeader("Authorization", "Bearer ${BuildConfig.TMDB_TOKEN_ID}")
        }
        return chain.proceed(requestBuilder.build())
    }
}