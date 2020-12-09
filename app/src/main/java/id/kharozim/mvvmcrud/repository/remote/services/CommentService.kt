package id.kharozim.mvvmcrud.repository.remote.services

import id.kharozim.mvvmcrud.models.CommentRequest
import id.kharozim.mvvmcrud.models.CommentResponse
import retrofit2.http.*

interface CommentService {
    @GET("comments")
    suspend fun getAllComment(): List<CommentResponse>

    @POST("comments")
    suspend fun addComment(@Body body: CommentRequest): CommentResponse

    @PUT("comments/{id}")
    suspend fun editComment(
        @Path("id") id: Int,
        @Body body: CommentRequest
    ): CommentResponse

    @DELETE("comments/{id}")
    suspend fun deleteComment(@Path("id") id: Int): CommentResponse
    
}