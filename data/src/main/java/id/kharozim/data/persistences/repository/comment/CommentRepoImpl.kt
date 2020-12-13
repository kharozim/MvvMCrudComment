package id.kharozim.data.persistences.repository.comment

import id.kharozim.data.persistences.contract.comment.CommentPersistenceContract
import id.kharozim.data.persistences.mapper.comment.CommentMapperInterface
import id.kharozim.domain.CommentDomain

class CommentRepoImpl(
    private val persistence: CommentPersistenceContract,
    private val mapper: CommentMapperInterface
) : CommentRepo {
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