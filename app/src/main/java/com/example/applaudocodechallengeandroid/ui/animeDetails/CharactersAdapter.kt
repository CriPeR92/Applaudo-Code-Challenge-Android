package com.example.applaudocodechallengeandroid.ui.animeDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applaudocodechallengeandroid.databinding.ItemCharacterBinding
import com.example.applaudocodechallengeandroid.model.AnimeCharacter

class CharactersAdapter(var list: ArrayList<AnimeCharacter>) :
    RecyclerView.Adapter<CharactersAdapter.CharactersAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(layoutInflater)
        return CharactersAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersAdapterViewHolder, position: Int) {
        holder.binding.character = list[position]
    }

    class CharactersAdapterViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return list.size
    }
}