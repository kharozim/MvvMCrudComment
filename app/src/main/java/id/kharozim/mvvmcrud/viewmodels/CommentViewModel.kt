package id.kharozim.mvvmcrud.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.kharozim.mvvmcrud.models.*
import id.kharozim.mvvmcrud.repository.CommentRepository
import id.kharozim.mvvmcrud.views.states.CommentState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CommentViewModel (private val commentRepository: CommentRepository) : ViewModel(){

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

    fun addComment(body: AddRequest){
        mutableState.value = CommentState.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = commentRepository.addComment(body)
                mutableState.postValue(CommentState.SuccessAddComment(response))


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
                val response = commentRepository.deleteComment(model.id)
                val model = response.toModel()
                mutableState.postValue(CommentState.SuccessDeleteComment(model))
            } catch (exc : Exception){

            }
        }
    }
}