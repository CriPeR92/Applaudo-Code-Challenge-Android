package com.example.applaudocodechallengeandroid.ui.mangaDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applaudocodechallengeandroid.databinding.ItemChaptersBinding
import com.example.applaudocodechallengeandroid.model.Chapter

class ChaptersAdapter(var list: ArrayList<Chapter>) :
    RecyclerView.Adapter<ChaptersAdapter.ChaptersAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChaptersAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemChaptersBinding.inflate(layoutInflater)
        return ChaptersAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChaptersAdapterViewHolder, position: Int) {
        holder.binding.chapter = list[position].attributes
    }

    class ChaptersAdapterViewHolder(val binding: ItemChaptersBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return list.size
    }
}