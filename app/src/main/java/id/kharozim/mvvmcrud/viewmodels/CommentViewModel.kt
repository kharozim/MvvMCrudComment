package id.kharozim.mvvmcrud.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.kharozim.mvvmcrud.models.*
import id.kharozim.mvvmcrud.repository.CommentRemoteRepository
import id.kharozim.mvvmcrud.repository.remote.responses.toModel
import id.kharozim.mvvmcrud.views.states.CommentState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CommentViewModel (private val commentRepository: CommentRemoteRepository) : ViewModel(){


    private val mutableState by lazy { MutableLiveData<CommentState>() }
    val state : LiveData<CommentState>
    get() = mutableState

    fun getAllComment(){
        mutableState.value = CommentState.Loading()
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = commentRepository.getAllComment()
                val list = response.asSequence().map { it.toModel() }.toList()
                mutableState.postValue(CommentState.SuccessGetAllComment(list))
            } catch (exc : Exception){
                exc.printStackTrace()
                mutableState.postValue(CommentState.Error(exc))
            }
        }
    }

    fun addComment(model: CommentModel){
        mutableState.value = CommentState.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = commentRepository.addComment(model.toRequest())
                val model = response.toModel()
                mutableState.postValue(CommentState.SuccessAddComment(model))


            } catch (exc : Exception){
                exc.printStackTrace()
            }
        }
    }

    fun editComment(model: CommentModel) {
        mutableState.value = CommentState.Loading()
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = commentRepository.editComment( model.id, model.toRequest())
                val model = response.toModel()
                mutableState.postValue(CommentState.SuccessEditComment(model))

            }catch (exc: Exception){
                exc.printStackTrace()
            }
        }
    }
    fun deleteComment(model: CommentModel){
        mutableState.value = CommentState.Loading()
        viewModelScope.launch(Dispatchers.IO){
            try {
                mutableState.postValue(CommentState.SuccessDeleteComment(model.id))
            } catch (exc : Exception){

            }
        }
    }
}