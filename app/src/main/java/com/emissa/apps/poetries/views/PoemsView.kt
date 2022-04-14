package com.emissa.apps.poetries.views

import android.os.Bundle
import android.util.Log
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
import com.emissa.apps.poetries.databinding.ListItemBinding
import com.emissa.apps.poetries.databinding.PoemItemBinding
import com.emissa.apps.poetries.model.Poem
import com.emissa.apps.poetries.utils.NetworkState


class PoemsView : BaseFragment(), ItemClickListener<Poem> {

    private val binding by lazy {
        FragmentPoetryBinding.inflate(layoutInflater)
    }

    private val poemAdapter by lazy {
        PoetryAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        poemAdapter.theViewHolderBinding = { item, viewBinding ->
            var view = viewBinding as PoemItemBinding
            view.poemAuthor.text = item.author
            view.poemTitle.text = item.title
            view.poemContent.text = item.lines.toString()
        }

        poemAdapter.theOnCreateViewHolder = { viewGroup ->
            PoemItemBinding.inflate(
                LayoutInflater.from(viewGroup.context), viewGroup, false
            )
        }

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
                        "LOADING LIST OF POEM...",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is NetworkState.SUCCESS<*> -> {
                    val poetry = state.response as List<Poem>
                    poemAdapter.setItems(poetry)
                }
                is NetworkState.ERROR -> {
                    Log.e("PoemViewsOnCreateView: ", state.error.localizedMessage )
                    Toast.makeText(
                        requireContext(),
                        state.error.localizedMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        poetryViewModel.getPoemByTitle("life")
//        poetryViewModel.getPoemByAuthors("William")
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onItemClicked(item: Poem) {
        poetryViewModel.getPoemByTitle(item.title)
        findNavController().navigate(R.id.action_Search_to_Details)
    }

}