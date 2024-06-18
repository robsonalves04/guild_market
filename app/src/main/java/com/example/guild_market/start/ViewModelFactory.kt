package com.example.guild_market.start

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.guild_market.services.IMarketProdutoService
import com.example.guild_market.services.MarketProdutoService
import com.example.guild_market.viewmodels.MarketProdutoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module


class Starting : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidLogger()
            androidContext(this@Starting)
            modules(module {

                single <IMarketProdutoService>{MarketProdutoService()}

                viewModel { MarketProdutoViewModel(get()) }

            })
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    companion object {
        lateinit var instance: Starting
            private set
    }
}
