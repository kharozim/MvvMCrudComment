package id.kharozim.mvvmcrud.presenter.infrastructure.di.modules

import id.kharozim.mvvmcrud.data.persistence.mapper.comment.CommentMapperImpl
import id.kharozim.mvvmcrud.data.persistence.mapper.comment.CommentMapperInterface
import org.koin.dsl.module

val mapperModule = module {
    single<CommentMapperInterface> { CommentMapperImpl() }
}