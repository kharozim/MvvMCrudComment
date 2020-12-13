package id.kharozim.usecase.cases.comment

import id.kharozim.data.persistences.repository.comment.CommentRepo
import id.kharozim.domain.CommentDomain

class CommentUseCaseImpl(
    private val repository: CommentRepo
) : CommentUseCase {
    override suspend fun getAllComment(): List<CommentDomain> {
        return repository.getAllComment()
    }

    override suspend fun insertComment(body: CommentDomain): CommentDomain {
        return repository.insertComment(body)
    }

    override suspend fun updateComment(id: Int, body: CommentDomain): CommentDomain {
        return repository.updateComment(id, body)
    }

    override suspend fun deleteComment(id: Int): CommentDomain {
        return repository.deleteComment(id)
    }

}