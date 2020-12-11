package id.kharozim.mvvmcrud.di

import com.google.gson.GsonBuilder
import id.kharozim.mvvmcrud.repository.CommentRemoteRepository
import id.kharozim.mvvmcrud.repository.remote.CommentRemoteRepositoryImpl
import id.kharozim.mvvmcrud.repository.remote.services.CommentService
import id.kharozim.mvvmcrud.viewmodels.CommentViewModel
import id.kharozim.mvvmcrud.views.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
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

/*    val connectTimeout: Long = 40// 20s
    val readTimeout: Long = 40 // 20s

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
        if (DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        okHttpClientBuilder.build()
        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
    single { provideHttpClient() }
    single { provideRetrofit(get(), Constants.BASE_URL) }*/

}
val serviceModule = module {

   /* fun setService(retrofit: Retrofit): CommentService {
        return retrofit.create(CommentService::class.java)
    }

    single { setService(retrofit = get()) }*/
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
