package id.kharozim.domain

data class CommentDomain(
    val name: String,
    val postId: Int = 0,
    val id: Int = 0,
    val body: String,
    val email: String
)