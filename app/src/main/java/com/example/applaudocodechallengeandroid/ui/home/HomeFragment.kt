package com.example.applaudocodechallengeandroid.ui.home

import android.content.Context
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
import com.example.applaudocodechallengeandroid.ui.MainActivity
import com.google.gson.Gson
import javax.inject.Inject

/**
 * Home Fragment: Shows applications main view, list of anime and manga
 * Anime and manga search (by default first call is "Naruto")
 * Favorite button
 */

class HomeFragment : Fragment() {

    private lateinit var mangaAdapter: MangaAdapter
    private lateinit var animeAdapter: AnimeAdapter
    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var viewModel: HomeViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity as MainActivity).mainComponent.inject(this)
    }

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
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Main anime call observer
         */
        viewModel.animeResponse.observe(binding.lifecycleOwner!!, {
            viewModel.hideProgressBarAnime.postValue(false)
            animeAdapter = AnimeAdapter(
                viewModel,
                it?.data ?: ArrayList()
            )
            binding.animeAdapter = animeAdapter
            animeAdapter.notifyDataSetChanged()
            viewModel.animeDispose.dispose()
        })

        /**
         * Main manga call observer
         */
        viewModel.mangaResponse.observe(binding.lifecycleOwner!!, {
            viewModel.hideProgressBarManga.postValue(false)
            mangaAdapter = MangaAdapter(
                viewModel,
                it?.data ?: ArrayList()
            )
            binding.mangaAdapter = mangaAdapter
            mangaAdapter.notifyDataSetChanged()
        })

        /**
         * Default error message
         */
        viewModel.error.observe(binding.lifecycleOwner!!, {
            Toast.makeText(this.context, it, Toast.LENGTH_LONG).show()
        })

        /**
         * Go to animeDetails Fragment
         */
        viewModel.selectedAnime.observe(binding.lifecycleOwner!!, {
            val bundle =
                bundleOf(
                    resources.getString(R.string.anime_) to Gson().toJson(it),
                    resources.getString(R.string.is_favorite) to false
                )
            findNavController().navigate(
                R.id.action_homeFragment_to_detailsFragment,
                bundle
            )
        })

        /**
         * Go to mangaDetails Fragment
         */
        viewModel.selectedManga.observe(binding.lifecycleOwner!!, {
            val bundle =
                bundleOf(
                    resources.getString(R.string.manga_) to Gson().toJson(it),
                    resources.getString(R.string.is_favorite) to false
                )
            findNavController().navigate(
                R.id.action_homeFragment_to_mangaDetailsFragment,
                bundle
            )
        })

        /**
         * Go to favorites Fragment
         */
        viewModel.showFavorites.observe(binding.lifecycleOwner!!, {
            val bundle =
                bundleOf(
                    resources.getString(R.string.anime_) to Gson().toJson(
                        viewModel.sharedPreferencesRepository.getAnimeFavorites(
                            context
                        )
                    ),
                    resources.getString(R.string.manga_) to Gson().toJson(
                        viewModel.sharedPreferencesRepository.getMangaFavorites(
                            context
                        )
                    )
                )
            findNavController().navigate(
                R.id.action_homeFragment_to_favoritesFragment,
                bundle
            )
        })

        /**
         * Adapters
         */
        if (!::animeAdapter.isInitialized) {
            viewModel.getSeries(resources.getString(R.string.default_search))
            animeAdapter = AnimeAdapter(viewModel, ArrayList())
            binding.animeAdapter = animeAdapter
        } else {
            binding.animeAdapter = animeAdapter
        }

        if (!::mangaAdapter.isInitialized) {
            viewModel.getManga(resources.getString(R.string.default_search))
            mangaAdapter = MangaAdapter(viewModel, ArrayList())
            binding.mangaAdapter = mangaAdapter
        } else {
            binding.mangaAdapter = mangaAdapter
        }

    }

}