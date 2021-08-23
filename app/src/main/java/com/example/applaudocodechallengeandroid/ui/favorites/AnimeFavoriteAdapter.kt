package com.example.applaudocodechallengeandroid.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.applaudocodechallengeandroid.databinding.ItemAnimeBinding
import com.example.applaudocodechallengeandroid.model.Anime

class AnimeFavoriteAdapter(private var fragment: FavoritesFragment, var list: ArrayList<Anime>) :
    RecyclerView.Adapter<AnimeFavoriteAdapter.AnimeFavoriteViewHolder>() {

    private lateinit var viewModel: FavoritesViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeFavoriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAnimeBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(fragment).get(FavoritesViewModel::class.java)
        return AnimeFavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimeFavoriteViewHolder, position: Int) {
        holder.binding.viewModelFavorite = viewModel
        holder.binding.anime = list[position]
    }

    class AnimeFavoriteViewHolder(val binding: ItemAnimeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return list.size
    }
}