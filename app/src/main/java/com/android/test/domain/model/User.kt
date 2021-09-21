package com.android.test.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(

    @Json(name = "login") var login : String,
    @Json(name = "avatar_url") var avatarUrl: String,
    @Json(name = "type") var type: String
)