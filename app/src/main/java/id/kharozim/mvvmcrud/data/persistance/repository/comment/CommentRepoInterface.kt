package id.kharozim.mvvmcrud.data.persistance.repository.comment

import id.kharozim.mvvmcrud.data.payload.api.comment.CommentRequest
import id.kharozim.mvvmcrud.domain.CommentDomain

interface CommentRepoInterface {
    suspend fun getAllComment(): List<CommentDomain>
    suspend fun insertComment(body : CommentRequest) : CommentDomain
    suspend fun updateComment(id : Int, body: CommentRequest) : CommentDomain
    suspend fun deleteComment(id : Int) : CommentDomain
}