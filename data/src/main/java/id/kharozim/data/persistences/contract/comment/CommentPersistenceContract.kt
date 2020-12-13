package id.kharozim.data.persistences.contract.comment

import id.kharozim.data.payload.api.comment.CommentRequest
import id.kharozim.data.payload.api.comment.CommentResponse

interface CommentPersistenceContract {

    suspend fun getAllComment(): List<CommentResponse>
    suspend fun insertComment(body: CommentRequest): CommentResponse
    suspend fun updateComment(id: Int, body: CommentRequest): CommentResponse
    suspend fun deleteComment(id: Int): CommentResponse

}