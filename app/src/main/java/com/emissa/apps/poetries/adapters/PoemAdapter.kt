package com.emissa.apps.poetries.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emissa.apps.poetries.databinding.PoemItemBinding
import com.emissa.apps.poetries.model.Poem

class PoemAdapter(
    private val poems: MutableList<Poem> = mutableListOf()
) : RecyclerView.Adapter<PoemViewHolder>() {

    fun setPoems(listOfPoem: List<Poem>) {
        poems.clear()
        poems.addAll(listOfPoem)
        notifyItemRangeChanged(0, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoemViewHolder {
        return PoemViewHolder(
            PoemItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PoemViewHolder, position: Int) {
        val poem = poems[position]
        holder.bind(poem)
    }

    override fun getItemCount(): Int = poems.size
}

class PoemViewHolder(
    private val binding: PoemItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(poem: Poem) {
        binding.apply {
            poemAuthor.text = poem.author
            poemTitle.text = poem.title
//            poemContent.text = poem.lines.toString()
        }
    }
}