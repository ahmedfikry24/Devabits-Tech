package com.example.devabitstech.di

import com.example.devabitstech.ui.screen.vm.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel<HomeViewModel> { HomeViewModel(get(), get()) }
}