package id.kharozim.mvvmcrud.data.persistence.mapper.comment

import id.kharozim.mvvmcrud.data.payload.api.comment.CommentRequest
import id.kharozim.mvvmcrud.data.payload.api.comment.CommentResponse
import id.kharozim.mvvmcrud.domain.CommentDomain

interface CommentMapperInterface {
    fun toListDomain(response: List<CommentResponse>) : List<CommentDomain>
    fun toDomain(response: CommentResponse) : CommentDomain
    fun toRequest(domain: CommentDomain) : CommentRequest
}