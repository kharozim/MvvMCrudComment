package id.kharozim.mvvmcrud.repository

import id.kharozim.mvvmcrud.data.payload.api.comment.CommentRequest
import id.kharozim.mvvmcrud.data.payload.api.comment.CommentResponse

interface CommentRemoteRepository {
    suspend fun getAllComment(): List<CommentResponse>
    suspend fun addComment(body : CommentRequest): CommentResponse
    suspend fun editComment(id: Int, body: CommentRequest) : CommentResponse
    suspend fun deleteComment(id : Int) : CommentResponse
}