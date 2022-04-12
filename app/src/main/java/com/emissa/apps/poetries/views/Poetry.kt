package com.emissa.apps.poetries.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.emissa.apps.poetries.databinding.FragmentPoetryBinding
import com.emissa.apps.poetries.model.Poem
import com.emissa.apps.poetries.utils.NetworkState


class Poetry : BaseFragment() {

    private val binding by lazy {
        FragmentPoetryBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.poemRecycler.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = poemAdapter
        }

        poetryViewModel.poetryData.observe(viewLifecycleOwner) { state ->
            when(state) {
                is NetworkState.LOADING -> {
                    Toast.makeText(
                        requireContext(),
                        "LOADING...",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is NetworkState.SUCCESS<*> -> {
                    val poetry = state.response as List<Poem>
                    poemAdapter.setPoems(poetry)
                }
                is NetworkState.ERROR -> {
                    Toast.makeText(
                        requireContext(),
                        state.error.localizedMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        poetryViewModel.getMultipleRandomPoem(5)
        // Inflate the layout for this fragment
        return binding.root
    }

}