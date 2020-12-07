package id.kharozim.mvvmcrud.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CommentModel(
    val name: String,
    val postId: Int = 0,
    val id: Int = 0,
    val body: String,
    val email: String
) : Parcelable

fun CommentModel.toRequest() = AddRequest(name, email, body)

