package com.android.test.domain.repository

import androidx.paging.PagingData
import com.android.test.domain.model.UserItemResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUserListPaging(name: String): Flow<PagingData<UserItemResponse>>
}
