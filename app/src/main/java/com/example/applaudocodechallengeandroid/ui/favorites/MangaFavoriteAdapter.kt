package com.example.applaudocodechallengeandroid.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.applaudocodechallengeandroid.databinding.ItemMangaBinding
import com.example.applaudocodechallengeandroid.model.Manga

class MangaFavoriteAdapter(private var fragment: FavoritesFragment, var list: ArrayList<Manga>) :
    RecyclerView.Adapter<MangaFavoriteAdapter.MangaFavoriteAdapterViewHolder>() {

    private lateinit var favoriteViewModel: FavoritesViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaFavoriteAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMangaBinding.inflate(layoutInflater)
        favoriteViewModel = ViewModelProvider(fragment).get(FavoritesViewModel::class.java)
        return MangaFavoriteAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MangaFavoriteAdapterViewHolder, position: Int) {
        holder.binding.viewModelFavorite = favoriteViewModel
        holder.binding.manga = list[position]
    }

    class MangaFavoriteAdapterViewHolder(val binding: ItemMangaBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return list.size
    }
}