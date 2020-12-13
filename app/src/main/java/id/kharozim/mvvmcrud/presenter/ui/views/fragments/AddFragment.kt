package id.kharozim.mvvmcrud.presenter.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import id.kharozim.data.persistences.contract.comment.CommentPersistenceContract
import id.kharozim.data.persistences.mapper.comment.CommentMapperImpl
import id.kharozim.data.persistences.mapper.comment.CommentMapperInterface
import id.kharozim.data.persistences.repository.comment.CommentRepo
import id.kharozim.data.persistences.repository.comment.CommentRepoImpl
import id.kharozim.domain.CommentDomain
import id.kharozim.mvvmcrud.databinding.FragmentAddBinding
import id.kharozim.mvvmcrud.presenter.infrastructure.api.comment.client.CommentClient
import id.kharozim.mvvmcrud.presenter.infrastructure.persistences.api.CommentPersistenceImpl
import id.kharozim.mvvmcrud.presenter.ui.states.CommentState
import id.kharozim.mvvmcrud.presenter.ui.viewmodels.CommentViewModel
import id.kharozim.mvvmcrud.presenter.ui.viewmodels.CommentViewModelFactory
import id.kharozim.usecase.cases.comment.CommentUseCase
import id.kharozim.usecase.cases.comment.CommentUseCaseImpl
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding

    private val service by lazy { CommentClient.service }
    private val persistence: CommentPersistenceContract by lazy { CommentPersistenceImpl(service) }
    private val mapper: CommentMapperInterface by lazy { CommentMapperImpl() }
    private val repository: CommentRepo by lazy { CommentRepoImpl(persistence, mapper) }
    private val useCase: CommentUseCase by lazy { CommentUseCaseImpl(repository) }
    private val viewModelFactory: CommentViewModelFactory by lazy { CommentViewModelFactory(useCase) }
    private val viewModel by viewModels<CommentViewModel> { viewModelFactory }
//    private val viewModel by viewModel<CommentViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false).apply {

            btnAdd.setOnClickListener {
                if (tieName.text.isNullOrEmpty() || tieEmail.text.isNullOrEmpty()) {
                    showMessage("email dan password tidak boleh kosong")
                } else {
                    val body = CommentDomain(
                        name = tieName.text.toString(),
                        email = tieEmail.text.toString(),
                        body = tieBody.text.toString()
                    )
                    viewModel.addComment(body)
                }
            }
        }

        setObserve()
        return binding.root
    }

    private fun setObserve() {
        viewModel.state.observe(viewLifecycleOwner) { commentState ->
            when (commentState) {
                is CommentState.Loading -> showLoading(true)
                is CommentState.SuccessAddComment -> {
                    showLoading(false)
                    showMessage("id ${commentState.domain.id} berhasil ditambahkan")
                }
                is CommentState.Error -> {
                    showLoading(false)
                    commentState.exception.message?.let { showMessage(it) }
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pbLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.btnAdd.visibility = if (!isLoading) View.VISIBLE else View.GONE
    }

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}