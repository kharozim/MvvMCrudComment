package id.kharozim.mvvmcrud.presenter.infrastructure.di.modules

import id.kharozim.data.persistences.repository.comment.CommentRepoImpl
import id.kharozim.data.persistences.repository.comment.CommentRepo
import org.koin.dsl.module

val repositoryModule = module {
    single<CommentRepo> { CommentRepoImpl(get(), get()) }
}