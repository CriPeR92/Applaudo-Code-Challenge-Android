package com.example.applaudocodechallengeandroid.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applaudocodechallengeandroid.databinding.ItemAnimeBinding
import com.example.applaudocodechallengeandroid.model.Anime

class AnimeAdapter(private var viewModel: HomeViewModel, var list: ArrayList<Anime>) :
    RecyclerView.Adapter<AnimeAdapter.HomeAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAnimeBinding.inflate(layoutInflater)
        return HomeAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeAdapterViewHolder, position: Int) {
        holder.binding.viewModel = viewModel
        holder.binding.anime = list[position]
    }

    class HomeAdapterViewHolder(val binding: ItemAnimeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return list.size
    }
}
