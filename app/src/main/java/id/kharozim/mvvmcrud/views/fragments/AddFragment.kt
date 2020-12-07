package id.kharozim.mvvmcrud.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.kharozim.mvvmcrud.R
import id.kharozim.mvvmcrud.databinding.FragmentAddBinding
import id.kharozim.mvvmcrud.databinding.FragmentHomeBinding

class AddFragment : Fragment() {

private lateinit var binding : FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)

        return binding.root
    }

}