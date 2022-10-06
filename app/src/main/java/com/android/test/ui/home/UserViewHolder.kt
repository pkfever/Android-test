package com.android.test.ui.home

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.android.test.databinding.ListItemUserBinding
import com.android.test.domain.model.UserItemResponse

class UserViewHolder(private val viewDataBinding: ListItemUserBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {

    fun onBindData(user: UserItemResponse) {

        viewDataBinding.userName.text = user.login
        viewDataBinding.userType.text = user.type
        viewDataBinding.imageUser.load(user.avatarUrl) {
            crossfade(durationMillis = 2000)
            transformations(RoundedCornersTransformation(12.5f))
        }
    }
}
