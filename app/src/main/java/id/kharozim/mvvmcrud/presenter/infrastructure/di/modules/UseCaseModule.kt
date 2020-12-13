package id.kharozim.mvvmcrud.presenter.infrastructure.di.modules

import id.kharozim.usecase.cases.comment.CommentUseCase
import id.kharozim.usecase.cases.comment.CommentUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module{
    single<CommentUseCase> { CommentUseCaseImpl(repository = get()) }
}