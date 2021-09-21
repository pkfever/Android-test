package com.android.test.data.source.remote.api

import com.android.test.data.source.remote.api.ApiConstants.Path.SEARCH
import com.android.test.data.source.remote.api.ApiConstants.Path.USERS
import com.android.test.data.source.remote.api.ApiConstants.Query.PAGE
import com.android.test.data.source.remote.api.ApiConstants.Query.PER_PAGE
import com.android.test.data.source.remote.api.ApiConstants.Query.QUERY
import com.android.test.domain.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("${SEARCH}/${USERS}")
    suspend fun getUserList(
        @Query(QUERY) query: String?,
        @Query(PER_PAGE) perPage: Int,
        @Query(PAGE) page: Int,
    ): UserResponse?

}