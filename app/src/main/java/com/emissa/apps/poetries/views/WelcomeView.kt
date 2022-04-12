package com.emissa.apps.poetries.views

import android.os.Bundle
import androidx.fragment.app.Fragment
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
            findNavController().navigate(R.id.action_welcome_to_authors)
        }

        binding.btnPoetry.setOnClickListener {
            findNavController().navigate(R.id.action_welcome_to_poetry)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}