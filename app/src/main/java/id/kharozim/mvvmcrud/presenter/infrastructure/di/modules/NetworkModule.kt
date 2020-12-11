package id.kharozim.mvvmcrud.presenter.infrastructure.di.modules

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import id.kharozim.mvvmcrud.BuildConfig
import id.kharozim.mvvmcrud.presenter.infrastructure.api.comment.service.CommentService
import id.kharozim.mvvmcrud.presenter.infrastructure.misc.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single<Gson> { GsonBuilder().setLenient().create() }
    single { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) }
    single { OkHttpClient.Builder().addInterceptor(get<HttpLoggingInterceptor>()).build() }
    single(named("CommentClient")) {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
    }
    single<CommentService> {
        get<Retrofit>(named("CommentClient")).create(CommentService::class.java)
    }
}