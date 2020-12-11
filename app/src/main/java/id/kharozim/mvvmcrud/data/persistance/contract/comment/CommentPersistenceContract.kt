package id.kharozim.mvvmcrud.data.persistance.contract.comment

import id.kharozim.mvvmcrud.data.payload.api.comment.CommentRequest
import id.kharozim.mvvmcrud.data.payload.api.comment.CommentResponse

interface CommentPersistenceContract {
    suspend fun getAllComment() : List<CommentResponse>
    suspend fun insertComment(body : CommentRequest) : CommentResponse
    suspend fun updateComment(id : Int, body: CommentRequest) : CommentResponse
    suspend fun deleteComment(id : Int) : CommentResponse
}