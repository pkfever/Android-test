package com.android.test.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.test.domain.model.UserItemResponse
import com.android.test.domain.repository.UserRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    suspend fun getUser(lbl: String): Flow<PagingData<UserItemResponse>> {
        return userRepository.getUserListPaging(lbl).cachedIn(viewModelScope)
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}
