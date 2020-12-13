package id.kharozim.mvvmcrud.presenter.ui.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.google.gson.Gson
import id.kharozim.domain.CommentDomain
import id.kharozim.mvvmcrud.presenter.ui.states.CommentState
import id.kharozim.usecase.cases.comment.CommentUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class CommentViewModelFactory(
    private val useCase: CommentUseCase
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CommentViewModel(useCase) as T
    }
}


class CommentViewModel(private val userCase: CommentUseCase) : ViewModel() {


    private val mutableState by lazy { MutableLiveData<CommentState>() }
    val state: LiveData<CommentState>
        get() = mutableState

    fun getAllComment() {
        mutableState.value = CommentState.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = userCase.getAllComment()
                mutableState.postValue(CommentState.SuccessGetAllComment(response))
            } catch (exc: Exception) {
                exc.printStackTrace()
                mutableState.postValue(CommentState.Error(exc))
            }
        }
    }

    fun addComment(domain: CommentDomain) {
        mutableState.value = CommentState.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {

                val response = userCase.insertComment(domain)
                mutableState.postValue(CommentState.SuccessAddComment(response))


            } catch (exc: Exception) {
                exc.printStackTrace()
            }
        }
    }

    fun editComment(domain: CommentDomain) {
        mutableState.value = CommentState.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = userCase.updateComment(domain.id, domain)
                mutableState.postValue(CommentState.SuccessEditComment(response))
                Log.e("editComment", "response : " + Gson().toJson(response))

            } catch (exc: Exception) {
                exc.printStackTrace()
            }
        }
    }

    fun deleteComment(domain: CommentDomain) {
        mutableState.value = CommentState.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mutableState.postValue(CommentState.SuccessDeleteComment(domain.id))
            } catch (exc: Exception) {

            }
        }
    }
}