package id.kharozim.data.persistences.mapper.comment

import id.kharozim.data.payload.api.comment.CommentRequest
import id.kharozim.data.payload.api.comment.CommentResponse
import id.kharozim.domain.CommentDomain

class CommentMapperImpl : CommentMapperInterface {
    override fun toListDomain(response: List<CommentResponse>): List<CommentDomain> {
        return response.asSequence().map { toDomain(it) }.toList()
    }

    override fun toDomain(response: CommentResponse): CommentDomain =
        CommentDomain(response.name, response.postId, response.id, response.body, response.email)

    override fun toRequest(domain: CommentDomain): CommentRequest {
        return CommentRequest(domain.name, domain.email, domain.body)
    }

}
