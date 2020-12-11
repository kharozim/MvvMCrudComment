package id.kharozim.mvvmcrud.presenter.infrastructure.di.modules

import id.kharozim.mvvmcrud.presenter.ui.viewmodels.CommentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CommentViewModel(userCase = get()) }
}