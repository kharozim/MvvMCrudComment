package id.kharozim.data.persistences.mapper.comment

import id.kharozim.data.payload.api.comment.CommentRequest
import id.kharozim.data.payload.api.comment.CommentResponse
import id.kharozim.domain.CommentDomain

interface CommentMapperInterface {

    fun toListDomain(response: List<CommentResponse>): List<CommentDomain>
    fun toDomain(response: CommentResponse): CommentDomain
    fun toRequest(domain: CommentDomain): CommentRequest

}