package com.nusantarian.moviecatalogue.core

import org.json.JSONObject
import retrofit2.Response

fun <T> retrofitErrorHandler(res: Response<T>): T {
    if (res.isSuccessful) {
        return res.body()!!
    } else {
        val errMsg = res.errorBody()?.string()?.let {
            JSONObject(it).getString("status_message") // get error message
        } ?: run {
            res.code().toString()
        }

        throw Exception(errMsg)
    }
}