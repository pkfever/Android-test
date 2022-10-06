package com.android.test.domain.response

import com.android.test.domain.model.User
import com.squareup.moshi.Json

class UserResponse {

    @Json(name = "total_count") var totalCount: Int = 0
    @Json(name = "items") var items: MutableList<User> = mutableListOf()
}
