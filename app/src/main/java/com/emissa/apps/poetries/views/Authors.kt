package com.emissa.apps.poetries.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emissa.apps.poetries.R
import com.emissa.apps.poetries.databinding.FragmentAuthorsBinding


class Authors : BaseFragment() {

    private val binding by lazy {
        FragmentAuthorsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

}