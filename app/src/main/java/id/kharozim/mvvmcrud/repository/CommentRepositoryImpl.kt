package id.kharozim.mvvmcrud.repository

import id.kharozim.mvvmcrud.models.AddRequest
import id.kharozim.mvvmcrud.models.CommentResponse
import id.kharozim.mvvmcrud.repository.services.CommentService

class CommentRepositoryImpl(private val service: CommentService) : CommentRepository {
    override suspend fun getAllComment(): List<CommentResponse> {
        return service.getAllComment()
    }

    override suspend fun addComment(body: AddRequest): CommentResponse {
        return service.addComment(body)
    }

}