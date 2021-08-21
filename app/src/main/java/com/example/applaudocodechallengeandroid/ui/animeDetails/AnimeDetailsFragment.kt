package com.example.applaudocodechallengeandroid.ui.animeDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.applaudocodechallengeandroid.R
import com.example.applaudocodechallengeandroid.databinding.FragmentAnimeDetailsBinding
import com.example.applaudocodechallengeandroid.model.*
import com.example.applaudocodechallengeandroid.ui.home.AnimeAdapter
import com.example.applaudocodechallengeandroid.ui.home.MangaAdapter
import com.google.gson.Gson
import org.koin.android.viewmodel.ext.android.viewModel

class AnimeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentAnimeDetailsBinding
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var episodesAdapter: EpisodesAdapter
    private lateinit var charactersAdapter: CharactersAdapter
    private val viewModel: DetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.selectedAnime.value = Gson().fromJson(arguments?.getString("anime"), Anime::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_anime_details,
            container,
            false
        )
        binding.viewModel = this.viewModel
        binding.anime = viewModel.selectedAnime.value?.attributes
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.genreResponse.observe(binding.lifecycleOwner!!, {
            viewModel.hideProgressGenre.postValue(false)
            genreAdapter = GenreAdapter(
                it.data ?: ArrayList()
            )
            binding.genreAdapter = genreAdapter
            genreAdapter.notifyDataSetChanged()
        })

        viewModel.episodesResponse.observe(binding.lifecycleOwner!!, {
            viewModel.hideProgressBarEpisodes.postValue(false)
            episodesAdapter = EpisodesAdapter(
                it.data ?: ArrayList()
            )
            binding.episodesAdapter = episodesAdapter
            episodesAdapter.notifyDataSetChanged()
        })

        viewModel.charactersResponse.observe(binding.lifecycleOwner!!, {
            viewModel.hideProgressBarCharacters.postValue(false)
            charactersAdapter = CharactersAdapter(
                it.included ?: ArrayList()
            )
            binding.charactersAdapter = charactersAdapter
            charactersAdapter.notifyDataSetChanged()
        })

//
//        viewModel.error.observe(binding.lifecycleOwner!!, {
//            Toast.makeText(this.context, it.message, Toast.LENGTH_LONG).show()
//        })

//        viewModel.seasons.observe(binding.lifecycleOwner!!, {
//            viewModel.hideProgress.postValue(false)
//            val bundle =
//                bundleOf(
//                    "Seasons" to Gson().toJson(it|),
//                    "Show" to Gson().toJson(viewModel.showSelected.value),
//                    "Favorite" to true
//                )
//            findNavController().navigate(
//                R.id.action_homeFragment_to_showFragment,
//                bundle
//            )
//        })

//        viewModel.showFavorites.observe(binding.lifecycleOwner!!, {
//            findNavController().navigate(R.id.action_homeFragment_to_favoritesFragment)
//        })

        if (!::genreAdapter.isInitialized) {
            viewModel.getGenres()
            genreAdapter = GenreAdapter(ArrayList())
            binding.genreAdapter = genreAdapter
        } else {
            binding.genreAdapter = genreAdapter
        }

        if (!::episodesAdapter.isInitialized) {
            viewModel.getEpisodes()
            episodesAdapter = EpisodesAdapter(ArrayList())
            binding.episodesAdapter = episodesAdapter
        } else {
            binding.episodesAdapter = episodesAdapter
        }

        if (!::charactersAdapter.isInitialized) {
            viewModel.getCharacters()
            charactersAdapter = CharactersAdapter(ArrayList())
            binding.charactersAdapter = charactersAdapter
        } else {
            binding.charactersAdapter = charactersAdapter
        }

    }

}