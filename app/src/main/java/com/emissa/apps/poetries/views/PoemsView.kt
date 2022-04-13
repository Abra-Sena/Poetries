package com.emissa.apps.poetries.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.emissa.apps.poetries.R
import com.emissa.apps.poetries.adapters.ItemClickListener
import com.emissa.apps.poetries.adapters.PoetryAdapter
import com.emissa.apps.poetries.databinding.FragmentPoetryBinding
import com.emissa.apps.poetries.model.Poem
import com.emissa.apps.poetries.utils.NetworkState


class PoemsView : BaseFragment(), ItemClickListener<Poem> {

    private val binding by lazy {
        FragmentPoetryBinding.inflate(layoutInflater)
    }

    private val poemAdapter by lazy {
        PoetryAdapter<Poem>(this)
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

        poetryViewModel.poemsData.observe(viewLifecycleOwner) { state ->
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
                    poemAdapter.setItems(poetry)
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

        poetryViewModel.getPoemByTitle("hope")
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onItemClicked(item: Poem) {
        poetryViewModel.getPoemByTitle(item.title)
        findNavController().navigate(R.id.action_PoemsView_to_PoemDetailsView)
    }

}