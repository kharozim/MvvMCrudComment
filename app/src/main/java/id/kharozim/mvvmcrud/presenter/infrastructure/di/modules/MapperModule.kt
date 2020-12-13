package id.kharozim.mvvmcrud.presenter.infrastructure.di.modules


import id.kharozim.data.persistences.mapper.comment.CommentMapperImpl
import id.kharozim.data.persistences.mapper.comment.CommentMapperInterface
import org.koin.dsl.module

val mapperModule = module {
    single<CommentMapperInterface> { CommentMapperImpl() }
}