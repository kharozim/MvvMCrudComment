package id.kharozim.mvvmcrud.models

data class CommentModel(
    val name: String,
    val postId: Int,
    val id: Int,
    val body: String,
    val email: String
)

fun CommentModel.toRequest() = AddRequest(name, email, body)

