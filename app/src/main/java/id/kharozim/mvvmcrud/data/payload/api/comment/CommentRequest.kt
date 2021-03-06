package id.kharozim.mvvmcrud.data.payload.api.comment

import com.google.gson.annotations.SerializedName

data class CommentRequest(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("body")
    val body: String
)