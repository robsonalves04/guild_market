package com.example.guild_market.start

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.guild_market.services.network_service.IMarketNetworkService
import com.example.guild_market.services.network_service.MarketNetworkService
import com.example.guild_market.services.produto_service.IMarketProdutoService
import com.example.guild_market.services.produto_service.MarketProdutoService
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
        //==Inicialização pelo Koin
        startKoin {
            androidLogger()
            androidContext(this@Starting)
            modules(module {
                //== Injeção de dependencia do Service
                single<IMarketProdutoService> { MarketProdutoService() }
                single<IMarketNetworkService> { MarketNetworkService() }

                //==Injeção do ViewModel
                viewModel { MarketProdutoViewModel(get(), get()) }
            })
        }
        //== Rodapé padrão do celular
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
    //== Variavel que faz com que a instacia inicie depois
    companion object {
        lateinit var instance: Starting
            private set
    }
}