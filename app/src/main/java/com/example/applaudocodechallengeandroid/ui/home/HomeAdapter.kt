package com.example.applaudocodechallengeandroid.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.applaudocodechallengeandroid.databinding.ItemSeriesBinding
import com.example.applaudocodechallengeandroid.model.MainSeriesResponse
import com.example.applaudocodechallengeandroid.model.Series

class HomeAdapter(private var fragment: HomeFragment, var list: ArrayList<Series>) :
    RecyclerView.Adapter<HomeAdapter.HomeAdapterViewHolder>() {

    private lateinit var vm: HomeViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSeriesBinding.inflate(layoutInflater)
        vm = ViewModelProvider(fragment).get(HomeViewModel::class.java)
        return HomeAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeAdapterViewHolder, position: Int) {
        holder.binding.viewModel = vm
        holder.binding.series = list[position].attributes
    }

    class HomeAdapterViewHolder(val binding: ItemSeriesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return list.size
    }
}
