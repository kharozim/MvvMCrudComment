package id.kharozim.mvvmcrud.usecase.cases.comment

import id.kharozim.mvvmcrud.data.payload.api.comment.CommentRequest
import id.kharozim.mvvmcrud.data.persistance.repository.comment.CommentRepoInterface
import id.kharozim.mvvmcrud.domain.CommentDomain

class CommentUseCaseImpl(
    private val repo: CommentRepoInterface
) : CommentUsecaseInterface {
    override suspend fun getAllComment(): List<CommentDomain> {
        return repo.getAllComment()
    }

    override suspend fun insertComment(body: CommentRequest): CommentDomain {
        return repo.insertComment(body)
    }

    override suspend fun updateComment(id: Int, body: CommentRequest): CommentDomain {
        return repo.updateComment(id, body)
    }

    override suspend fun deleteComment(id: Int): CommentDomain {
        return repo.deleteComment(id)
    }

}