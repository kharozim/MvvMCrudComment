package id.kharozim.mvvmcrud.repository.clients

import com.google.gson.GsonBuilder
import id.kharozim.mvvmcrud.repository.services.CommentService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private val interceptor by lazy { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)}

    private val client by lazy { OkHttpClient.Builder().addInterceptor(interceptor).build()}

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(client)
            .build()
    }
    val service by lazy {
        retrofit.create(CommentService::class.java)
    }
}