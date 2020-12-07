package id.kharozim.mvvmcrud.repository

import id.kharozim.mvvmcrud.models.AddRequest
import id.kharozim.mvvmcrud.models.AddResponse
import id.kharozim.mvvmcrud.models.CommentModel
import id.kharozim.mvvmcrud.models.CommentResponse
import id.kharozim.mvvmcrud.repository.services.CommentService

class CommentRepositoryImpl(private val service: CommentService) : CommentRepository {
    override suspend fun getAllComment(): List<CommentResponse> {
        return service.getAllComment()
    }

    override suspend fun addComment(body: AddRequest): AddResponse {
        return service.addComment(body)
    }

    override suspend fun editComment(id: Int, body: AddRequest): CommentResponse {
        return service.editComment(id, body)
    }

}