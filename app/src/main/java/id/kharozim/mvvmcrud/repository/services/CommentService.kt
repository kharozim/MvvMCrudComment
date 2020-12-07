package id.kharozim.mvvmcrud.repository.services

import id.kharozim.mvvmcrud.models.AddRequest
import id.kharozim.mvvmcrud.models.CommentResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CommentService {
    @GET("comments")
   suspend fun getAllComment() : List<CommentResponse>

    @POST("comments")
    suspend fun addComment(@Body body: AddRequest) : CommentResponse

}