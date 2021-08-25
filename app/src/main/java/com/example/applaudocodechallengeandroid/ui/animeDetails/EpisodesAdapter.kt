package com.example.applaudocodechallengeandroid.ui.animeDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applaudocodechallengeandroid.databinding.ItemEpisodesBinding
import com.example.applaudocodechallengeandroid.model.Episode

class EpisodesAdapter(var list: ArrayList<Episode>) :
    RecyclerView.Adapter<EpisodesAdapter.EpisodesAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemEpisodesBinding.inflate(layoutInflater)
        return EpisodesAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodesAdapterViewHolder, position: Int) {
        holder.binding.episode = list[position].attributes
    }

    class EpisodesAdapterViewHolder(val binding: ItemEpisodesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return list.size
    }
}