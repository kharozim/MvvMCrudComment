package id.kharozim.mvvmcrud.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.kharozim.mvvmcrud.models.*
import id.kharozim.mvvmcrud.repository.CommentRepository
import id.kharozim.mvvmcrud.views.states.AddState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel(private val commentRepository: CommentRepository) : ViewModel() {

    private val mutableState by lazy { MutableLiveData<AddState>() }
    val state : LiveData<AddState>
    get() {return mutableState}

    fun addComment(commentModel: CommentModel){
        mutableState.value = AddState.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
//                val response = commentRepository.addComment()


            } catch (exc : Exception){
                exc.printStackTrace()
            }
        }
    }

}
