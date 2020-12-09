package id.kharozim.mvvmcrud.repository.remote

import id.kharozim.mvvmcrud.repository.remote.requests.CommentRequest
import id.kharozim.mvvmcrud.models.CommentResponse
import id.kharozim.mvvmcrud.repository.CommentRemoteRepository
import id.kharozim.mvvmcrud.repository.remote.services.CommentService

class CommentRemoteRepositoryImpl(private val service: CommentService) : CommentRemoteRepository {
    override suspend fun getAllComment(): List<CommentResponse> {
        return service.getAllComment()
    }

    override suspend fun addComment(body: CommentRequest): CommentResponse {
        return service.addComment(body)
    }


    override suspend fun editComment(id: Int, body: CommentRequest): CommentResponse {
        return service.editComment(id, body)
    }

    override suspend fun deleteComment(id: Int): CommentResponse {
        return service.deleteComment(id)
    }
}
