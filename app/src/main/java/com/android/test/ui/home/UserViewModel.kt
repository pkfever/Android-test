package com.android.test.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.android.test.domain.model.UserItemResponse
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import com.android.test.domain.repository.UserRepository

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    suspend fun getUser(lbl: String): Flow<PagingData<UserItemResponse>> {
        return userRepository.getUserListPaging(lbl).cachedIn(viewModelScope)
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    companion object {
        private val TAG = UserViewModel::class.java.name
    }
}