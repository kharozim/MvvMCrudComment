package id.kharozim.mvvmcrud.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import id.kharozim.mvvmcrud.databinding.FragmentHomeBinding
import id.kharozim.mvvmcrud.models.CommentModel
import id.kharozim.mvvmcrud.repository.CommentRepository
import id.kharozim.mvvmcrud.repository.CommentRepositoryImpl
import id.kharozim.mvvmcrud.repository.clients.ApiClient
import id.kharozim.mvvmcrud.viewmodels.CommentViewModel
import id.kharozim.mvvmcrud.viewmodels.CommentViewModelFactory
import id.kharozim.mvvmcrud.views.adapters.CommentAdapter
import id.kharozim.mvvmcrud.views.states.CommentState
import java.lang.Exception

class HomeFragment : Fragment(), CommentAdapter.CommentListener {

    private lateinit var binding: FragmentHomeBinding

    private val adapter by lazy { CommentAdapter(requireContext(), this) }
    private val service by lazy { ApiClient.service }
    private val remoteRepo: CommentRepository by lazy { CommentRepositoryImpl(service) }
    private val viewModelFactory by lazy { CommentViewModelFactory(remoteRepo) }
    private val viewModel by viewModels<CommentViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            btAdd.setOnClickListener { findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddFragment()) }

            rvHome.adapter = adapter

            viewModel.state.observe(viewLifecycleOwner) {
                when (it) {
                    is CommentState.Loading -> showLoading(true)
                    is CommentState.Error -> {
                        showLoading(false)
                        Toast.makeText(
                            requireContext(),
                            it.exception.message ?: "Oops Somethink Wrong",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is CommentState.SuccessGetAllComment -> {
                        showLoading(false)
                        adapter.list = it.list.toMutableList()

                    }
                    is CommentState.SuccessEditComment ->{
                        showLoading(false)
                        adapter.editComment(it.model)
                    }
                    else -> throw Exception("Unsupported  state type")
                }
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllComment()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pbLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onEdit(model: CommentModel) {
        viewModel.editComment(model)
    }

}