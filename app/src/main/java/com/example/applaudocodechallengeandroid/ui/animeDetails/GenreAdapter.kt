package com.example.applaudocodechallengeandroid.ui.animeDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applaudocodechallengeandroid.databinding.ItemGenreBinding
import com.example.applaudocodechallengeandroid.model.Genre

class GenreAdapter(var list: ArrayList<Genre>) :
    RecyclerView.Adapter<GenreAdapter.GenreAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemGenreBinding.inflate(layoutInflater)

        return GenreAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreAdapterViewHolder, position: Int) {
        holder.binding.genre = list[position].attributes
    }

    class GenreAdapterViewHolder(val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return list.size
    }
}