package id.kharozim.mvvmcrud.presenter.infrastructure.di.modules

import id.kharozim.mvvmcrud.data.persistence.repository.comment.CommentRepoImpl
import id.kharozim.mvvmcrud.data.persistence.repository.comment.CommentRepoInterface
import org.koin.dsl.module

val repositoryModule = module {
    single<CommentRepoInterface> { CommentRepoImpl(get(), get()) }
}