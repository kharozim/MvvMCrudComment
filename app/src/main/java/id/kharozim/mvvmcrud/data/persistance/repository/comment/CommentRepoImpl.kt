package id.kharozim.mvvmcrud.data.persistance.repository.comment

import id.kharozim.mvvmcrud.data.payload.api.comment.CommentRequest
import id.kharozim.mvvmcrud.data.persistance.contract.comment.CommentPersistenceContract
import id.kharozim.mvvmcrud.data.persistance.mapper.comment.CommentMapperInterface
import id.kharozim.mvvmcrud.domain.CommentDomain

class CommentRepoImpl(
    private val persistence: CommentPersistenceContract,
    private val mapper: CommentMapperInterface
) : CommentRepoInterface {
    override suspend fun getAllComment(): List<CommentDomain> {
        return mapper.toListDomain(persistence.getAllComment())
    }

    override suspend fun insertComment(body: CommentDomain): CommentDomain {
        return mapper.toDomain(persistence.insertComment(mapper.toRequest(body)))
    }

    override suspend fun updateComment(id: Int, body: CommentDomain): CommentDomain {
        return mapper.toDomain(persistence.updateComment(id, mapper.toRequest(body)))

    }

    override suspend fun deleteComment(id: Int): CommentDomain {
        return mapper.toDomain(persistence.deleteComment(id))

    }
}