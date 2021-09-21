package com.android.test.domain.response

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.test.data.source.remote.api.ApiService
import com.android.test.domain.model.UserItemResponse
import com.android.test.domain.model.UserListMapper

class UserListPagingSource(
    private val service: ApiService,
    private val mapper: UserListMapper,
    private val queryParams: String
) :
    PagingSource<Int, UserItemResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserItemResponse> {
//        Log.v("Load", "Load")
        val page = params.key ?: 1
        return try {
            val jsonResponse = service.getUserList(queryParams, 9, page)
            val response = jsonResponse?.let {
                mapper.toCryptoListResponse(it.items)
            } ?: mutableListOf()

            val nextKey = if (jsonResponse == null) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                page + 1
            }
            LoadResult.Page(
                data = response,
                prevKey = null, // Only paging forward.
                // assume that if a full page is not loaded, that means the end of the data
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserItemResponse>): Int? {
        return null
    }
}