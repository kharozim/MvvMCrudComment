package id.kharozim.mvvmcrud.presenter.infrastructure.api.comment.client

import android.util.Log
import com.google.gson.GsonBuilder
import id.kharozim.mvvmcrud.BuildConfig.DEBUG
import id.kharozim.mvvmcrud.presenter.infrastructure.api.comment.service.CommentService
import id.kharozim.mvvmcrud.presenter.infrastructure.misc.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CommentClient {
    companion object {

        private val gson by lazy { GsonBuilder().setLenient().create() }
        private val loggingInterceptor by lazy {
            HttpLoggingInterceptor { Log.e("API_LOG", it) }.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }
        private val client by lazy {
            OkHttpClient.Builder()
                .apply { if (DEBUG) addInterceptor(loggingInterceptor) }
                .build()
        }
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
        }

        val service: CommentService by lazy { retrofit.create(CommentService::class.java) }
    }
}
