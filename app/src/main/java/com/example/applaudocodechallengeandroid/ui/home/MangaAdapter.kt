package com.example.applaudocodechallengeandroid.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.applaudocodechallengeandroid.databinding.ItemMangaBinding
import com.example.applaudocodechallengeandroid.model.Manga

class MangaAdapter(private var fragment: HomeFragment, var list: ArrayList<Manga>) :
    RecyclerView.Adapter<MangaAdapter.MangaAdapterViewHolder>() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMangaBinding.inflate(layoutInflater)
        homeViewModel = ViewModelProvider(fragment).get(HomeViewModel::class.java)
        return MangaAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MangaAdapterViewHolder, position: Int) {
        holder.binding.viewModel = homeViewModel
        holder.binding.manga = list[position]
    }

    class MangaAdapterViewHolder(val binding: ItemMangaBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return list.size
    }
}