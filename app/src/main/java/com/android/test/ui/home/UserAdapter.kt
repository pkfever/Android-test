package com.android.test.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.android.test.databinding.ListItemUserBinding
import com.android.test.domain.model.UserItemResponse

class UserAdapter : PagingDataAdapter<UserItemResponse, UserViewHolder>(DiffUtilCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ListItemUserBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    object DiffUtilCallBack : DiffUtil.ItemCallback<UserItemResponse>() {
        override fun areItemsTheSame(
            oldItem: UserItemResponse,
            newItem: UserItemResponse
        ): Boolean {
            return oldItem.login == newItem.login
        }

        override fun areContentsTheSame(
            oldItem: UserItemResponse,
            newItem: UserItemResponse
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        holder.onBindData(getItem(position)!!)
    }
}
