package com.example.applaudocodechallengeandroid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.applaudocodechallengeandroid.R
import com.example.applaudocodechallengeandroid.databinding.FragmentHomeBinding
import com.google.gson.Gson
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var mangaAdapter: MangaAdapter
    private lateinit var animeAdapter: AnimeAdapter
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        binding.viewModel = this.viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.animeResponse.observe(binding.lifecycleOwner!!, {
            viewModel.hideProgressBarAnime.postValue(false)
            animeAdapter = AnimeAdapter(
                this,
                it.data ?: ArrayList()
            )
            binding.animeAdapter = animeAdapter
            animeAdapter.notifyDataSetChanged()
        })

        viewModel.mangaResponse.observe(binding.lifecycleOwner!!, {
            viewModel.hideProgressBarManga.postValue(false)
            mangaAdapter = MangaAdapter(
                this,
                it.data ?: ArrayList()
            )
            binding.mangaAdapter = mangaAdapter
            mangaAdapter.notifyDataSetChanged()
        })

        viewModel.error.observe(binding.lifecycleOwner!!, {
            Toast.makeText(this.context, it.message, Toast.LENGTH_LONG).show()
        })

        viewModel.selectedAnime.observe(binding.lifecycleOwner!!, {
            val bundle =
                bundleOf(
                    "anime" to Gson().toJson(viewModel.selectedAnime.value)
                )
            findNavController().navigate(
                R.id.action_homeFragment_to_detailsFragment,
                bundle
            )
        })

        viewModel.selectedManga.observe(binding.lifecycleOwner!!, {
            viewModel.hideProgressBarManga.postValue(false)
            val bundle =
                bundleOf(
                    "manga" to Gson().toJson(it),
                )
            findNavController().navigate(
                R.id.action_homeFragment_to_detailsFragment,
                bundle
            )
        })

//        viewModel.showFavorites.observe(binding.lifecycleOwner!!, {
//            findNavController().navigate(R.id.action_homeFragment_to_favoritesFragment)
//        })

        if (!::animeAdapter.isInitialized) {
            viewModel.getSeries(resources.getString(R.string.default_search))
            animeAdapter = AnimeAdapter(this, ArrayList())
            binding.animeAdapter = animeAdapter
        } else {
            binding.animeAdapter = animeAdapter
        }

        if (!::mangaAdapter.isInitialized) {
            viewModel.getManga(resources.getString(R.string.default_search))
            mangaAdapter = MangaAdapter(this, ArrayList())
            binding.mangaAdapter = mangaAdapter
        } else {
            binding.mangaAdapter = mangaAdapter
        }

    }

}