package com.emissa.apps.poetries.views

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.emissa.apps.poetries.adapters.PoetryAdapter
import com.emissa.apps.poetries.model.Poem
import com.emissa.apps.poetries.viewmodel.PoetryViewModel
import org.koin.androidx.viewmodel.ext.android.sharedStateViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

open class BaseFragment : Fragment() {

    protected val poetryViewModel: PoetryViewModel by sharedViewModel()

}

/**
 *
 *  //Initiate the adapter with type
    var mAdapter = BaseAdapter<String>()

    //Sample data
    mAdapter.listOfItems = mutableListOf("2","3","2","3","2","3")


    mAdapter.expressionViewHolderBinding = {eachItem,viewBinding->
    //eachItem will provide the each item in the list, in this case its a string type
    //cast the viewBinding with yout layout binding class
    var view = viewBinding as LayoutBindingBinding
    view.tvNumber.text = string
    //you can use click listener on root or any button
    view.root.setOnClickListener {
    //Click item value is eachItem
    }
    }

    mAdapter.expressionOnCreateViewHolder = {viewGroup->
    //Inflate the layout and send it to the adapter
    LayoutBindingBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
    }

    //finally put the adapter to recyclerview
    recyclerView.apply {
    layoutManager = LinearLayoutManager(context)
    adapter = mAdapter
    }
 *
 */