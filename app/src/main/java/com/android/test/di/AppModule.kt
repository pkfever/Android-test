package com.android.test.di

import com.android.test.domain.model.UserListMapper
import com.android.test.ui.home.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {

    single { UserListMapper() }

    single { createUserRepository(get(), get()) }

    viewModel { UserViewModel(get()) }
}