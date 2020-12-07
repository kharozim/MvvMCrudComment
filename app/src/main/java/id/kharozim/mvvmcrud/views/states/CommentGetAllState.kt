package id.kharozim.mvvmcrud.views.states

import id.kharozim.mvvmcrud.models.CommentModel

sealed class CommentGetAllState {
    data class Loading(val message: String = "Loading...") : CommentGetAllState()
    data class Error(val exception: Exception) : CommentGetAllState()
    data class SuccessGetAllComment(val list : List<CommentModel>) :CommentGetAllState()
}