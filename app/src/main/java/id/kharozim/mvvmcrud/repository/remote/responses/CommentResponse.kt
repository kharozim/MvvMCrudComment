package id.kharozim.mvvmcrud.repository.remote.responses

import com.google.gson.annotations.SerializedName
import id.kharozim.mvvmcrud.models.CommentModel

data class CommentResponse(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("postId")
    val postId: Int,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("body")
    val body: String,

    @field:SerializedName("email")
    val email: String
)

fun CommentResponse.toModel() = CommentModel(name, postId, id, body, email)


