package com.example.applaudocodechallengeandroid.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.applaudocodechallengeandroid.databinding.ItemAnimeBinding
import com.example.applaudocodechallengeandroid.model.Anime

class AnimeAdapter(private var fragment: HomeFragment, var list: ArrayList<Anime>) :
    RecyclerView.Adapter<AnimeAdapter.HomeAdapterViewHolder>() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAnimeBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(fragment).get(HomeViewModel::class.java)
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
