package id.kharozim.mvvmcrud.presenter.infrastructure.di.modules

import id.kharozim.mvvmcrud.usecase.cases.comment.CommentUseCaseImpl
import id.kharozim.mvvmcrud.usecase.cases.comment.CommentUsecaseInterface
import org.koin.dsl.module

val useCaseModule = module{
    single<CommentUsecaseInterface> { CommentUseCaseImpl(repo = get()) }
}