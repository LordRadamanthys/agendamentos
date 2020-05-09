package com.example.mulheresag.infra

import org.json.JSONObject

fun formatResponseError(value: String?): JSONObject {
    return JSONObject(value)
}