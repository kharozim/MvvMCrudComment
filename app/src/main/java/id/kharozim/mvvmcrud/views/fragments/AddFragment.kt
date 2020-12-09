package id.kharozim.mvvmcrud.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import id.kharozim.mvvmcrud.databinding.FragmentAddBinding
import id.kharozim.mvvmcrud.models.CommentModel
import id.kharozim.mvvmcrud.repository.CommentRepository
import id.kharozim.mvvmcrud.repository.remote.CommentRepositoryImpl
import id.kharozim.mvvmcrud.repository.remote.clients.ApiClient
import id.kharozim.mvvmcrud.viewmodels.CommentViewModel
import id.kharozim.mvvmcrud.viewmodels.CommentViewModelFactory
import id.kharozim.mvvmcrud.views.states.CommentState

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private val service by lazy { ApiClient.service }
    private val repository: CommentRepository by lazy { CommentRepositoryImpl(service) }
    private val viewModelFactory by lazy { CommentViewModelFactory(repository) }
    private val viewModel by viewModels<CommentViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false).apply {

            btnAdd.setOnClickListener {
                if (tieName.text.isNullOrEmpty() || tieEmail.text.isNullOrEmpty()) {
                    showMessage("email dan password tidak boleh kosong")
                } else {
                    val body = CommentModel(name = tieName.text.toString(),email =  tieEmail.text.toString(), body = tieBody.text.toString())
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
                    showMessage("id ${commentState.model.id} berhasil ditambahkan")
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