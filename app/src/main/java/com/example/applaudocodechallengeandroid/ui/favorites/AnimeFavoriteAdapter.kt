package com.example.applaudocodechallengeandroid.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.applaudocodechallengeandroid.databinding.ItemAnimeFavoriteBinding
import com.example.applaudocodechallengeandroid.model.Anime

class AnimeFavoriteAdapter(private var viewModel: FavoritesViewModel, var list: ArrayList<Anime>) :
    RecyclerView.Adapter<AnimeFavoriteAdapter.AnimeFavoriteViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeFavoriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAnimeFavoriteBinding.inflate(layoutInflater)
        return AnimeFavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimeFavoriteViewHolder, position: Int) {
        holder.binding.viewModelFavorite = viewModel
        holder.binding.anime = list[position]
    }

    class AnimeFavoriteViewHolder(val binding: ItemAnimeFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return list.size
    }
}