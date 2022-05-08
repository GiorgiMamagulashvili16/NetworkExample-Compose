package com.example.networktask8.app

import android.app.Application
import com.example.networktask8.di.networkModule
import com.example.networktask8.di.useCaseModule
import com.example.networktask8.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(networkModule, useCaseModule, vmModule))
        }
    }
}