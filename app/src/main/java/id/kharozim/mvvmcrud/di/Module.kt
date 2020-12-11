package id.kharozim.mvvmcrud.di

import com.google.gson.GsonBuilder
import id.kharozim.mvvmcrud.repository.CommentRemoteRepository
import id.kharozim.mvvmcrud.repository.remote.CommentRemoteRepositoryImpl
import id.kharozim.mvvmcrud.presenter.infrastructure.api.comment.service.CommentService
import id.kharozim.mvvmcrud.presenter.ui.viewmodels.CommentViewModel
import id.kharozim.mvvmcrud.presenter.infrastructure.misc.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
    val interceptor by lazy { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) }
    val client by lazy { OkHttpClient.Builder().addInterceptor(interceptor).build() }
    fun setRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(client)
            .build()
    }
    single { setRetrofit(baseUrl = Constants.BASE_URL) }

}
val serviceModule = module {

    single { get<Retrofit>().create(CommentService::class.java) }

}


val repositoryModule = module {
    fun setCommentRepository(service: CommentService): CommentRemoteRepository {
        return CommentRemoteRepositoryImpl(service)
    }
    single { setCommentRepository(get()) }
}

val viewModelModule = module {
    viewModel { CommentViewModel(commentRepository = get()) }
}
