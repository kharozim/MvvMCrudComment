package id.kharozim.mvvmcrud.presenter.ui.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import id.kharozim.data.persistences.contract.comment.CommentPersistenceContract
import id.kharozim.data.persistences.mapper.comment.CommentMapperImpl
import id.kharozim.data.persistences.mapper.comment.CommentMapperInterface
import id.kharozim.data.persistences.repository.comment.CommentRepo
import id.kharozim.data.persistences.repository.comment.CommentRepoImpl
import id.kharozim.domain.CommentDomain
import id.kharozim.mvvmcrud.databinding.FragmentEditBinding
import id.kharozim.mvvmcrud.presenter.infrastructure.api.comment.client.CommentClient
import id.kharozim.mvvmcrud.presenter.infrastructure.persistences.api.CommentPersistenceImpl
import id.kharozim.mvvmcrud.presenter.ui.states.CommentState
import id.kharozim.mvvmcrud.presenter.ui.viewmodels.CommentViewModel
import id.kharozim.mvvmcrud.presenter.ui.viewmodels.CommentViewModelFactory
import id.kharozim.usecase.cases.comment.CommentUseCase
import id.kharozim.usecase.cases.comment.CommentUseCaseImpl
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditFragment : Fragment() {

    private lateinit var binding: FragmentEditBinding
    private val service by lazy { CommentClient.service }
    private val persistence: CommentPersistenceContract by lazy { CommentPersistenceImpl(service) }
    private val mapper: CommentMapperInterface by lazy { CommentMapperImpl() }
    private val repository: CommentRepo by lazy { CommentRepoImpl(persistence, mapper) }
    private val useCase: CommentUseCase by lazy { CommentUseCaseImpl(repository) }
    private val viewModelFactory: CommentViewModelFactory by lazy { CommentViewModelFactory(useCase) }
    private val viewModel by viewModels<CommentViewModel> { viewModelFactory }

    //    private val args by navArgs<EditFragmentArgs>()
//    private val viewModel by viewModel<CommentViewModel>()
    private val args by lazy { arguments?.get("data") }
    private lateinit var model: CommentDomain

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEditBinding.inflate(inflater, container, false).apply {
            model = Gson().fromJson(args.toString(), CommentDomain::class.java)
            tieName.setText(model.name)
            tieEmail.setText(model.email)
            tieBody.setText(model.body)

                Toast.makeText(requireContext(), "${model.email}", Toast.LENGTH_SHORT).show()

            btEdit.setOnClickListener {
                if (tieName.text.isNullOrEmpty() || tieEmail.text.isNullOrEmpty()) {
                    showMessage("email dan password tidak boleh kosong")
                } else {
                    val body = CommentDomain(id = model.id, name = tieName.text.toString(), email = tieEmail.text.toString(),body =  tieBody.text.toString())
                    viewModel.editComment(body)
                    Log.e("EditFragment", "onCreateView: ", )
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