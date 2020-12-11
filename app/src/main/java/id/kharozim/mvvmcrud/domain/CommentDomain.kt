package id.kharozim.mvvmcrud.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CommentDomain(
    val name: String,
    val postId: Int = 0,
    val id: Int = 0,
    val body: String,
    val email: String
) : Parcelable