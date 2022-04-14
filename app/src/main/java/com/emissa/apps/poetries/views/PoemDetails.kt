package com.emissa.apps.poetries.views

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.emissa.apps.poetries.databinding.FragmentPoemDetailsBinding
import com.emissa.apps.poetries.utils.NetworkState


class PoemDetails : BaseFragment() {

    private val binding by lazy {
        FragmentPoemDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.progressBarDetails.visibility = View.VISIBLE

        poetryViewModel.poem.observe(viewLifecycleOwner){ poemToDisplay->
            Log.i("ViewPoem", poemToDisplay.toString())
            binding.apply {
                poemAuthorView.text = poemToDisplay?.author
                poemTitleView.text = poemToDisplay?.title
                poemContentView.text = poemToDisplay?.lines?.toString()
                poemContentView.movementMethod = ScrollingMovementMethod()
            }
        }
        binding.progressBarDetails.visibility = View.GONE

        // Inflate the layout for this fragment
        return binding.root
    }
}