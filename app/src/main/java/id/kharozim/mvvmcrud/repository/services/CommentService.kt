package id.kharozim.mvvmcrud.repository.services

import id.kharozim.mvvmcrud.models.AddRequest
import id.kharozim.mvvmcrud.models.AddResponse
import id.kharozim.mvvmcrud.models.CommentResponse
import retrofit2.http.*

interface CommentService {
    @GET("comments")
    suspend fun getAllComment(): List<CommentResponse>

    @POST("comments")
    suspend fun addComment(@Body body: AddRequest): AddResponse

    @PUT("comments/{id}")
    fun editComment(
        @Path("id") id: Int,
        @Body body: AddRequest
    ): CommentResponse

}