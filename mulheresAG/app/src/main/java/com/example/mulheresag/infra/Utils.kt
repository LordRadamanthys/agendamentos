package com.example.mulheresag.infra

import android.Manifest
import okhttp3.OkHttpClient
import org.json.JSONObject

fun formatResponseError(value: String?): JSONObject {
    return JSONObject(value)
}


fun picassoAuth(): OkHttpClient {
    return OkHttpClient.Builder()
        .authenticator { route, response ->
            val credential = App.userToken
            response.request().newBuilder()
                .header("Authorization", credential)
                .build()
        }
        .build()
}

fun getListPermissions(): Array<String> {
    return arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
}