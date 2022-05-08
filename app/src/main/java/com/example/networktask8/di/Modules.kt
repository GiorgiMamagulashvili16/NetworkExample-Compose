package com.example.networktask8.di

import com.example.networktask8.data.data_source.provideRetrofitInstance
import com.example.networktask8.data.data_source.provideService
import com.example.networktask8.data.repository.ContentRepositoryImpl
import com.example.networktask8.domain.repository.ContentRepository
import com.example.networktask8.domain.use_case.GetRandomUserUseCase
import com.example.networktask8.domain.use_case.GetResourcesUseCase
import com.example.networktask8.domain.use_case.GetUsersUseCase
import com.example.networktask8.presentation.user_screen.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single {
        provideRetrofitInstance()
    }
    single {
        provideService(get())
    }
    factory<ContentRepository> { ContentRepositoryImpl(get()) }
}
val useCaseModule = module {
    single { GetRandomUserUseCase(get()) }
    single { GetResourcesUseCase(get()) }
    single { GetUsersUseCase(get()) }
}
val vmModule = module {
    viewModel {
        UserViewModel(get(), get(), get())
    }
}