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