package id.kharozim.mvvmcrud.repository

import id.kharozim.mvvmcrud.models.CommentRequest
import id.kharozim.mvvmcrud.models.CommentResponse

interface CommentRepository {
    suspend fun getAllComment(): List<CommentResponse>
    suspend fun addComment(body : CommentRequest): CommentResponse
    suspend fun editComment(id: Int, body: CommentRequest) : CommentResponse
    suspend fun deleteComment(id : Int) : CommentResponse
}