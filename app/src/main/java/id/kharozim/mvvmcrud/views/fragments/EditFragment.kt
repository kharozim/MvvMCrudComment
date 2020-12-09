package id.kharozim.mvvmcrud.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import id.kharozim.mvvmcrud.databinding.FragmentEditBinding
import id.kharozim.mvvmcrud.models.CommentModel
import id.kharozim.mvvmcrud.viewmodels.CommentViewModel
import id.kharozim.mvvmcrud.views.states.CommentState
import org.koin.android.viewmodel.ext.android.viewModel

class EditFragment : Fragment() {

    private lateinit var binding: FragmentEditBinding
 /*   private val service by lazy { ApiClient.service }
    private val repository: CommentRemoteRepository by lazy { CommentRemoteRepositoryImpl(service) }
    private val viewModelFactory by lazy { CommentViewModelFactory(repository) }
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
                    val body = CommentModel(id = args.comment.id, name = tieName.text.toString(), email = tieEmail.text.toString(),body =  tieBody.text.toString())
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
                    showMessage("${it.model.id} berhasil di update")
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