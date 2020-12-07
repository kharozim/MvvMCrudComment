package id.kharozim.mvvmcrud.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.kharozim.mvvmcrud.models.CommentModel
import id.kharozim.mvvmcrud.repository.CommentRepository

class CommentViewModelFactory(
    private val remoteRepo : CommentRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CommentViewModel(remoteRepo) as T
    }
}