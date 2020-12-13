package id.kharozim.mvvmcrud.presenter.infrastructure.api.comment.service


import id.kharozim.data.payload.api.comment.CommentRequest
import id.kharozim.data.payload.api.comment.CommentResponse
import retrofit2.http.*

interface CommentService {
    @GET("comments")
    suspend fun getAllComment(): List<CommentResponse>

    @POST("comments")
    suspend fun insertComment(@Body body: CommentRequest): CommentResponse

    @PUT("comments/{id}")
    suspend fun updateComment(
        @Path("id") id: Int,
        @Body body: CommentRequest
    ): CommentResponse

    @DELETE("comments/{id}")
    suspend fun deleteComment(@Path("id") id: Int): CommentResponse
    
}