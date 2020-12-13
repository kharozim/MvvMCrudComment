package id.kharozim.mvvmcrud.presenter.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import id.kharozim.domain.CommentDomain
import id.kharozim.mvvmcrud.R
import id.kharozim.mvvmcrud.databinding.FragmentHomeBinding
import id.kharozim.mvvmcrud.presenter.ui.adapters.CommentAdapter
import id.kharozim.mvvmcrud.presenter.ui.states.CommentState
import id.kharozim.mvvmcrud.presenter.ui.viewmodels.CommentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), CommentAdapter.CommentListener {

    private lateinit var binding: FragmentHomeBinding
    private val adapter by lazy { CommentAdapter(requireContext(), this) }

   /* private val service by lazy { CommentClient.service }
    private val persistence: CommentPersistenceContract by lazy { CommentPersistenceImpl(service) }
    private val mapper: CommentMapperInterface by lazy { CommentMapperImpl() }
    private val repository: CommentRepo by lazy { CommentRepoImpl(persistence, mapper) }
    private val useCase: CommentUseCase by lazy { CommentUseCaseImpl(repository) }
    private val viewModelFactory: CommentViewModelFactory by lazy { CommentViewModelFactory(useCase) }
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
                            it.exception.message ?: "Oops Something Wrong",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is CommentState.SuccessGetAllComment -> {
                        showLoading(false)
                        adapter.list = it.list.toMutableList()
                    }
                    is CommentState.SuccessEditComment -> {
                        showLoading(false)
                        adapter.editComment(it.domain)
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

    override fun onClick(model: CommentDomain) {

        //this is to send data from bundle and Gson
        val bundle = bundleOf("data" to  Gson().toJson(model))
        findNavController().navigate(R.id.action_homeFragment_to_editFragment,bundle)

        viewModel.editComment(model)
    }

    override fun onDelete(model: CommentDomain) {
        viewModel.deleteComment(model)
    }

}