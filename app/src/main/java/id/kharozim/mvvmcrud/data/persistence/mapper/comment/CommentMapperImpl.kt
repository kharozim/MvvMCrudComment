package id.kharozim.mvvmcrud.data.persistence.mapper.comment

import id.kharozim.mvvmcrud.data.payload.api.comment.CommentRequest
import id.kharozim.mvvmcrud.data.payload.api.comment.CommentResponse
import id.kharozim.mvvmcrud.domain.CommentDomain

class CommentMapperImpl : CommentMapperInterface {


    //Response ke  list Domain (hati2 saat proses .map) yang dipanggil toDomain
    override fun toListDomain(response: List<CommentResponse>): List<CommentDomain> {
        return response.asSequence().map { toDomain(it) }.toList()
    }
    // Response ke Domain
    override fun toDomain(response: CommentResponse): CommentDomain {
        return CommentDomain(
            response.name,
            response.postId,
            response.id,
            response.body,
            response.email
        )
    }

    // Domain ke Request
    override fun toRequest(domain: CommentDomain): CommentRequest {
        return CommentRequest(domain.name, domain.email, domain.body)
    }
}