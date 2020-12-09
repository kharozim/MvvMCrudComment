package id.kharozim.mvvmcrud

import android.app.Application
import id.kharozim.mvvmcrud.di.networkModule
import id.kharozim.mvvmcrud.di.repositoryModule
import id.kharozim.mvvmcrud.di.serviceModule
import id.kharozim.mvvmcrud.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

//Application Class
class CommentApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CommentApp)
            modules(networkModule, serviceModule, repositoryModule, viewModelModule)
        }
    }

}