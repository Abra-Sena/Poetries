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
import com.emissa.apps.poetries.databinding.FragmentListViewsBinding
import com.emissa.apps.poetries.databinding.ListItemBinding
import com.emissa.apps.poetries.model.Poem
import com.emissa.apps.poetries.utils.NetworkState


class ListFragment : BaseFragment(), ItemClickListener<String> {

    private val binding by lazy {
        FragmentListViewsBinding.inflate(layoutInflater)
    }

    private val lisItemAdapter by lazy {
        PoetryAdapter<String>(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        lisItemAdapter.theViewHolderBinding = { rowItem, viewBinding ->
            // use the rowItem to handle click listeners
            var view = viewBinding as ListItemBinding
            view.listItemValue.text = rowItem
        }

        lisItemAdapter.theOnCreateViewHolder = { viewGroup ->
            ListItemBinding.inflate(
                LayoutInflater.from(viewGroup.context), viewGroup, false
            )
        }

        binding.listViewRecycler.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = lisItemAdapter
        }

//        poetryViewModel.authorsData.observe(viewLifecycleOwner) { state ->
//            when(state) {
//                is NetworkState.LOADING -> {
//                    Toast.makeText(
//                        requireContext(),
//                        "LOADING AUTHORS LIST...",
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//                is NetworkState.SUCCESS<*> -> {
//                    val author = state.response as List<String>
//                    lisItemAdapter.setItems(author)
//                }
//                is NetworkState.ERROR -> {
//                    Toast.makeText(
//                        requireContext(),
//                        state.error.localizedMessage,
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//            }
//        }
//
//        poetryViewModel.authorsData.observe(viewLifecycleOwner) { state ->
//            when(state) {
//                is NetworkState.LOADING -> {
//                    Toast.makeText(
//                        requireContext(),
//                        "LOADING TITLES LIST...",
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//                is NetworkState.SUCCESS<*> -> {
//                    val title = state.response as List<String>
//                    lisItemAdapter.setItems(title)
//                }
//                is NetworkState.ERROR -> {
//                    Toast.makeText(
//                        requireContext(),
//                        state.error.localizedMessage,
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//            }
//        }
//
//        poetryViewModel.getAuthors()
//        poetryViewModel.getAllPoemTitles()

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onItemClicked(item: String) {
        poetryViewModel.getPoemByTitle(item)
        findNavController().navigate(R.id.action_PoemsView_to_PoemDetailsView)
    }

}