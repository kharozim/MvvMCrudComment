package id.kharozim.mvvmcrud.presenter.ui.states

import id.kharozim.domain.CommentDomain


sealed class CommentState {
    data class Loading(val message: String = "Loading...") : CommentState()
    data class Error(val exception: Exception) : CommentState()
    data class SuccessGetAllComment(val list: List<CommentDomain>) : CommentState()
    data class SuccessAddComment(val domain: CommentDomain) : CommentState()
    data class SuccessEditComment(val domain: CommentDomain) : CommentState()
    data class SuccessDeleteComment(val id: Int) : CommentState()
}