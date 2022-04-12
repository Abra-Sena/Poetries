package com.emissa.apps.poetries.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emissa.apps.poetries.databinding.AuthorItemBinding
import com.emissa.apps.poetries.databinding.PoemItemBinding
import com.emissa.apps.poetries.model.Authors
import com.emissa.apps.poetries.model.Poem



class AuthorViewHolder(
    private val binding: AuthorItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(author: Authors) {
        binding.apply {
            authorName.text = author.toString()
        }
    }
}
