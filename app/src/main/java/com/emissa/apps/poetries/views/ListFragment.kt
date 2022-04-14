package com.emissa.apps.poetries.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.emissa.apps.poetries.R
import com.emissa.apps.poetries.adapters.ItemClickListener
import com.emissa.apps.poetries.adapters.PoetryAdapter
import com.emissa.apps.poetries.databinding.FragmentListViewsBinding
import com.emissa.apps.poetries.databinding.ListItemBinding


class ListFragment : BaseFragment(), ItemClickListener<String> {

    private val binding by lazy {
        FragmentListViewsBinding.inflate(layoutInflater)
    }

    private val lisItemAdapter by lazy {
        PoetryAdapter(this)
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

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onItemClicked(item: String) {
        poetryViewModel.getPoemByTitle(item)
        findNavController().navigate(R.id.action_ListView_to_Details)
    }

}