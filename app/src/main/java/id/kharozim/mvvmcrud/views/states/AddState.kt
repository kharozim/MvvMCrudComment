package id.kharozim.mvvmcrud.views.states

import id.kharozim.mvvmcrud.models.CommentModel

sealed class AddState{
    data class Loading (val message : String = "Loading...") : AddState()
    data class Error (val exception: Exception) : AddState()
    data class OnSuccessAdd (val commentModel: CommentModel) : AddState()
}