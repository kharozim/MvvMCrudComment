package id.kharozim.mvvmcrud.repository.remote

import id.kharozim.mvvmcrud.models.AddRequest
import id.kharozim.mvvmcrud.models.AddResponse
import id.kharozim.mvvmcrud.models.CommentResponse
import id.kharozim.mvvmcrud.repository.CommentRepository
import id.kharozim.mvvmcrud.repository.remote.services.CommentService

class CommentRepositoryImpl(private val service: CommentService) : CommentRepository {
    override suspend fun getAllComment(): List<CommentResponse> {
        return service.getAllComment()
    }

    override suspend fun addComment(body: AddRequest): CommentResponse {
        return service.addComment(body)
    }


    override suspend fun editComment(id: Int, body: AddRequest): CommentResponse {
        return service.editComment(id, body)
    }

    override suspend fun deleteComment(id: Int): CommentResponse {
        return service.deleteComment(id)
    }
}