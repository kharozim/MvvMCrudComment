package id.kharozim.mvvmcrud.views.states

import id.kharozim.mvvmcrud.models.AddResponse
import id.kharozim.mvvmcrud.models.CommentModel
import id.kharozim.mvvmcrud.models.CommentResponse

sealed class CommentState {
    data class Loading(val message: String = "Loading...") : CommentState()
    data class Error(val exception: Exception) : CommentState()
    data class SuccessGetAllComment(val list: List<CommentModel>) : CommentState()
    data class SuccessAddComment(val model: CommentModel) : CommentState()
    data class SuccessEditComment(val model: CommentModel) : CommentState()
    data class SuccessDeleteComment(val id: Int) : CommentState()
}