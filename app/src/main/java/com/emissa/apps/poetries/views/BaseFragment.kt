package com.emissa.apps.poetries.views

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.emissa.apps.poetries.adapters.PoemAdapter
import com.emissa.apps.poetries.viewmodel.PoetryViewModel
import org.koin.androidx.viewmodel.ext.android.sharedStateViewModel

open class BaseFragment : Fragment() {

    protected val poetryViewModel: PoetryViewModel by sharedStateViewModel()

    protected val poemAdapter by lazy {
        PoemAdapter()
    }
}