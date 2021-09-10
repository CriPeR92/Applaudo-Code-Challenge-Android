package com.example.applaudocodechallengeandroid.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.applaudocodechallengeandroid.databinding.ItemMangaFavoriteBinding
import com.example.applaudocodechallengeandroid.model.Manga

class MangaFavoriteAdapter(private var viewModel: FavoritesViewModel, var list: ArrayList<Manga>) :
    RecyclerView.Adapter<MangaFavoriteAdapter.MangaFavoriteAdapterViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MangaFavoriteAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMangaFavoriteBinding.inflate(layoutInflater)
        return MangaFavoriteAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MangaFavoriteAdapterViewHolder, position: Int) {
        holder.binding.viewModelFavorite = viewModel
        holder.binding.manga = list[position]
    }

    class MangaFavoriteAdapterViewHolder(val binding: ItemMangaFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return list.size
    }
}