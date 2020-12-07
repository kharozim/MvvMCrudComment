package id.kharozim.mvvmcrud.repository

import id.kharozim.mvvmcrud.models.AddRequest
import id.kharozim.mvvmcrud.models.AddResponse
import id.kharozim.mvvmcrud.models.CommentResponse

interface CommentRepository {
    suspend fun getAllComment(): List<CommentResponse>
    suspend fun addComment(body : AddRequest): AddResponse
    suspend fun editComment(id: Int, body: AddRequest) : CommentResponse
}