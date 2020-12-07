package id.kharozim.mvvmcrud.models

import com.google.gson.annotations.SerializedName

data class AddRequest(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("body")
    val body: String
)