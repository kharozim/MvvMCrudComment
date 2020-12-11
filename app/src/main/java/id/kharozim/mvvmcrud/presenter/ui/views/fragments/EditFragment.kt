package id.kharozim.mvvmcrud.presenter.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import id.kharozim.mvvmcrud.databinding.FragmentEditBinding
import id.kharozim.mvvmcrud.domain.CommentDomain
import id.kharozim.mvvmcrud.presenter.ui.states.CommentState
import id.kharozim.mvvmcrud.presenter.ui.viewmodels.CommentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditFragment : Fragment() {

    private lateinit var binding: FragmentEditBinding
/*    private val service by lazy { CommentClient.service}
    private val persistence : CommentPersistenceContract by lazy { CommentPersistenceImpl(service) }
    private val mapper : CommentMapperInterface by lazy { CommentMapperImpl() }
    private val repository : CommentRepoInterface by lazy { CommentRepoImpl(persistence, mapper) }
    private val useCase : CommentUsecaseInterface by lazy { CommentUseCaseImpl(repository) }
    private val viewModelFactory : CommentViewModelFactory by lazy { CommentViewModelFactory(useCase) }
    private val viewModel by viewModels<CommentViewModel> { viewModelFactory }*/
    private val args by navArgs<EditFragmentArgs>()
    private val viewModel by viewModel<CommentViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEditBinding.inflate(inflater, container, false).apply {
            tieName.setText(args.comment.name)
            tieEmail.setText(args.comment.email)
            tieBody.setText(args.comment.body)

            btEdit.setOnClickListener {
                if (tieName.text.isNullOrEmpty() || tieEmail.text.isNullOrEmpty()) {
                    showMessage("email dan password tidak boleh kosong")
                } else {
                    val body = CommentDomain(id = args.comment.id, name = tieName.text.toString(), email = tieEmail.text.toString(),body =  tieBody.text.toString())
                    viewModel.editComment(body)
                }
            }
        }
        setObserve()
        return binding.root
    }

    private fun setObserve() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is CommentState.Loading -> showLoading(true)
                is CommentState.Error -> {
                    showLoading(false)
                    it.exception.message?.let { showMessage(it) }
                }
                is CommentState.SuccessEditComment -> {
                    showLoading(false)
                    showMessage("${it.domain.id} berhasil di update")
                }
            }
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pbLoading.visibility = if (isLoading == true) View.VISIBLE else View.GONE
        binding.btEdit.visibility = if (!isLoading == true) View.VISIBLE else View.GONE
    }


}