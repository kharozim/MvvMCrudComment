package id.kharozim.mvvmcrud.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.kharozim.mvvmcrud.models.toModel
import id.kharozim.mvvmcrud.repository.CommentRepository
import id.kharozim.mvvmcrud.views.states.CommentGetAllState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CommentViewModel (private val commentRepository: CommentRepository) : ViewModel(){

    private val mutableState by lazy { MutableLiveData<CommentGetAllState>() }
    val state : LiveData<CommentGetAllState>
    get() = mutableState

    fun getAllComment(){
        mutableState.value = CommentGetAllState.Loading()
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = commentRepository.getAllComment()
                val list = response.asSequence().map { it.toModel() }.toList()
                mutableState.postValue(CommentGetAllState.SuccessGetAllComment(list))
            } catch (exc : Exception){
                exc.printStackTrace()
                mutableState.postValue(CommentGetAllState.Error(exc))
            }
        }
    }
}