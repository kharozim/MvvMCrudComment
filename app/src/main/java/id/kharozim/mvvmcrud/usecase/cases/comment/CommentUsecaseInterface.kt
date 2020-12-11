package id.kharozim.mvvmcrud.usecase.cases.comment

import id.kharozim.mvvmcrud.data.payload.api.comment.CommentRequest
import id.kharozim.mvvmcrud.domain.CommentDomain
import okhttp3.RequestBody

interface CommentUsecaseInterface {
    suspend fun getAllComment(): List<CommentDomain>
    suspend fun insertComment(body: CommentRequest): CommentDomain
    suspend fun updateComment(id: Int, body: CommentRequest): CommentDomain
    suspend fun deleteComment(id: Int): CommentDomain
}