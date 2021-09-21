package com.android.test.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.test.data.source.remote.api.ApiService
import com.android.test.domain.model.UserItemResponse
import com.android.test.domain.model.UserListMapper
import com.android.test.domain.repository.UserRepository
import com.android.test.domain.response.UserListPagingSource
import kotlinx.coroutines.flow.Flow

class UserRepositoryImp(
    private val apiService: ApiService,
    private val mapper: UserListMapper
) : UserRepository {

    override suspend fun getUserListPaging(
        name: String
    ): Flow<PagingData<UserItemResponse>> = Pager(
        PagingConfig(9, enablePlaceholders = false)
    ) {
        UserListPagingSource(
            apiService,
            mapper,
            name
        )
    }.flow

}