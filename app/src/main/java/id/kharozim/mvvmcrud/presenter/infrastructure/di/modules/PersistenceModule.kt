package id.kharozim.mvvmcrud.presenter.infrastructure.di.modules

import id.kharozim.data.persistences.contract.comment.CommentPersistenceContract
import id.kharozim.mvvmcrud.presenter.infrastructure.persistences.api.CommentPersistenceImpl
import org.koin.dsl.module

val persistenceModule = module {

    single<CommentPersistenceContract> { CommentPersistenceImpl(service = get()) }
}