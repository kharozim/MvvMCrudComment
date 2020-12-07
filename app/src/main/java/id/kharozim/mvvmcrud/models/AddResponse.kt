package id.kharozim.mvvmcrud.models

import com.google.gson.annotations.SerializedName

data class AddResponse(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("body")
	val body: String,

	@field:SerializedName("email")
	val email: String
)