package com.emissa.apps.poetries.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.emissa.apps.poetries.R
import com.emissa.apps.poetries.databinding.FragmentWelcomeViewBinding


class WelcomeView : BaseFragment() {

    private val binding by lazy {
        FragmentWelcomeViewBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.btnAuthors.setOnClickListener {
            binding.searchView.visibility = View.VISIBLE
            val action = WelcomeViewDirections.actionWelcomeToAuthors()
            findNavController().navigate(action)
        }

        binding.btnPoetry.setOnClickListener {
            binding.searchView.visibility = View.VISIBLE
            val action = WelcomeViewDirections.actionWelcomeToPoetry()
            findNavController().navigate(action)
        }

        binding.btnRandom.setOnClickListener {
            poetryViewModel.getRandomPoem()
            val action = WelcomeViewDirections.actionWelcomeViewToPoemDetailsView()
            findNavController().navigate(action)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}