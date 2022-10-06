package com.android.test.domain.model

class UserListMapper {

    fun toCryptoListResponse(json: List<User>): List<UserItemResponse> {
        with(json) {
            val items = this.map { toCryptoItemResponse(it) }
            return items.sortedBy { it.type }
        }
    }

    private fun toCryptoItemResponse(json: User): UserItemResponse {
        with(json) {
            return UserItemResponse(
                login = login,
                avatarUrl = avatarUrl,
                type = type
            )
        }
    }
}
