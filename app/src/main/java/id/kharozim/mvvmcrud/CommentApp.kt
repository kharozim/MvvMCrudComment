package id.kharozim.mvvmcrud

import android.app.Application
import id.kharozim.mvvmcrud.presenter.infrastructure.di.modules.*
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
            modules(
                networkModule,
                persistenceModule,
                mapperModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }

}