package id.kharozim.mvvmcrud.repository.remote

import id.kharozim.mvvmcrud.data.payload.api.comment.CommentRequest
import id.kharozim.mvvmcrud.repository.CommentRemoteRepository
import id.kharozim.mvvmcrud.data.payload.api.comment.CommentResponse
import id.kharozim.mvvmcrud.presenter.infrastructure.api.comment.service.CommentService

class CommentRemoteRepositoryImpl(private val service: CommentService) : CommentRemoteRepository {
    override suspend fun getAllComment(): List<CommentResponse> {
        return service.getAllComment()
    }

    override suspend fun addComment(body: CommentRequest): CommentResponse {
        return service.insertComment(body)
    }


    override suspend fun editComment(id: Int, body: CommentRequest): CommentResponse {
        return service.updateComment(id, body)
    }

    override suspend fun deleteComment(id: Int): CommentResponse {
        return service.deleteComment(id)
    }
}
