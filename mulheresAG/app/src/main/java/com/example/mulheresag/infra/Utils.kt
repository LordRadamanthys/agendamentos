package com.example.mulheresag.infra

import okhttp3.OkHttpClient
import org.json.JSONObject

fun formatResponseError(value: String?): JSONObject {
    return JSONObject(value)
}


fun picassoAuth():OkHttpClient{
    val okHttpClient = OkHttpClient.Builder()
        .authenticator { route, response ->
            val credential = App.userToken
            response.request().newBuilder()
                .header("Authorization", credential)
                .build()
        }
        .build()
    return okHttpClient
}