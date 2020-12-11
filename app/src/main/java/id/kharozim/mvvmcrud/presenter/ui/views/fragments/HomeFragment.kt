package id.kharozim.mvvmcrud.presenter.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import id.kharozim.mvvmcrud.R
import id.kharozim.mvvmcrud.databinding.FragmentHomeBinding
import id.kharozim.mvvmcrud.domain.CommentModel
import id.kharozim.mvvmcrud.presenter.ui.viewmodels.CommentViewModel
import id.kharozim.mvvmcrud.presenter.ui.adapters.CommentAdapter
import id.kharozim.mvvmcrud.presenter.ui.states.CommentState
import id.kharozim.mvvmcrud.views.fragments.HomeFragmentDirections
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.androidx.viewmodel.compat.ScopeCompat.viewModel

class HomeFragment : Fragment(), CommentAdapter.CommentListener {

    private lateinit var binding: FragmentHomeBinding

    private val adapter by lazy { CommentAdapter(requireContext(), this) }
/*    private val service by lazy { ApiClient.service }
    private val remoteRepo: CommentRemoteRepository by lazy { CommentRemoteRepositoryImpl(service) }
    private val viewModelFactory by lazy { CommentViewModelFactory(remoteRepo) }
    private val viewModel by viewModels<CommentViewModel> { viewModelFactory }*/
    private val viewModel by viewModel<CommentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            btAdd.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_addFragment) }

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
                        adapter. list = it.list.toMutableList()
                    }
                    is CommentState.SuccessEditComment -> {
                        showLoading(false)
                        adapter.editComment(it.model)
                    }
                    is CommentState.SuccessDeleteComment -> {
                        showLoading(false)
                        adapter.deleteComment(it.id)
                        showMesage("${it.id} berhasil dihapus")
                    }
                    else -> throw Exception("Unsupported  state type")
                }
            }
        }

        return binding.root
    }

    private fun showMesage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllComment()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pbLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onClick(model: CommentModel) {
        val action = HomeFragmentDirections.actionHomeFragmentToEditFragment(model)
        findNavController().navigate(action)
        viewModel.editComment(model)
    }

    override fun onDelete(model: CommentModel) {
        viewModel.deleteComment(model)
    }

}