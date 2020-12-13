package id.kharozim.mvvmcrud.presenter.infrastructure.persistences.api


import id.kharozim.data.payload.api.comment.CommentRequest
import id.kharozim.data.payload.api.comment.CommentResponse
import id.kharozim.data.persistences.contract.comment.CommentPersistenceContract
import id.kharozim.mvvmcrud.presenter.infrastructure.api.comment.service.CommentService

class CommentPersistenceImpl(
    private val service: CommentService
) : CommentPersistenceContract {
    override suspend fun getAllComment(): List<CommentResponse> {
        return service.getAllComment()
    }

    override suspend fun insertComment(body: CommentRequest): CommentResponse {
        return service.insertComment(body)

    }

    override suspend fun updateComment(id: Int, body: CommentRequest): CommentResponse {
        return service.updateComment(id, body)

    }

    override suspend fun deleteComment(id: Int): CommentResponse {
        return service.deleteComment(id)
    }

}