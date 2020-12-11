package id.kharozim.mvvmcrud.data.persistance.repository.comment

import id.kharozim.mvvmcrud.data.payload.api.comment.CommentRequest
import id.kharozim.mvvmcrud.data.persistance.contract.comment.CommentPersistanceContract
import id.kharozim.mvvmcrud.data.persistance.mapper.comment.CommentMapperInterface
import id.kharozim.mvvmcrud.domain.CommentDomain

class CommentRepoImpl(
    private val persistance: CommentPersistanceContract,
    private val mapper: CommentMapperInterface
) : CommentRepoInterface {
    override suspend fun getAllComment(): List<CommentDomain> {
        return mapper.toListDomain(persistance.getAllComment())
    }

    override suspend fun insertComment(body: CommentRequest): CommentDomain {
        return mapper.toDomain(persistance.insertComment(body))
    }

    override suspend fun updateComment(id: Int, body: CommentRequest): CommentDomain {
        return mapper.toDomain(persistance.updateComment(id,body))
    }

    override suspend fun deleteComment(id: Int): CommentDomain {
        return mapper.toDomain(persistance.deleteComment(id))

    }
}