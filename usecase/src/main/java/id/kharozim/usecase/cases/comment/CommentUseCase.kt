package id.kharozim.usecase.cases.comment

import id.kharozim.domain.CommentDomain

interface CommentUseCase {
    suspend fun getAllComment(): List<CommentDomain>
    suspend fun insertComment(body: CommentDomain): CommentDomain
    suspend fun updateComment(id: Int, body: CommentDomain): CommentDomain
    suspend fun deleteComment(id: Int): CommentDomain
}