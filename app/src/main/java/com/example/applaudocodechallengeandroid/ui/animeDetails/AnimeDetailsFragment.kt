package com.example.applaudocodechallengeandroid.ui.animeDetails

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.applaudocodechallengeandroid.R
import com.example.applaudocodechallengeandroid.databinding.FragmentAnimeDetailsBinding
import com.example.applaudocodechallengeandroid.model.*
import com.example.applaudocodechallengeandroid.ui.MainActivity
import com.google.gson.Gson
import javax.inject.Inject


/**
 * Anime Details Fragment: Shows all details of an Anime selected
 */

class AnimeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentAnimeDetailsBinding
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var episodesAdapter: EpisodesAdapter
    private lateinit var charactersAdapter: CharactersAdapter

    @Inject
    lateinit var viewModel: DetailsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).mainComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.selectedAnime.value = Gson().fromJson(
            arguments?.getString(resources.getString(R.string.anime_)),
            Anime::class.java
        )
        viewModel.isFavorite.value =
            arguments?.getBoolean(resources.getString(R.string.is_favorite))
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

        /**
         * Open youtube app or web browser
         */
        viewModel.videoUrl.observe(binding.lifecycleOwner!!, {
            val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$it"))
            val intentBrowser =
                Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=$it"))
            try {
                this.startActivity(intentApp)
            } catch (ex: ActivityNotFoundException) {
                this.startActivity(intentBrowser)
            }
        })

        /**
         * Share Anime Name
         */
        viewModel.share.observe(binding.lifecycleOwner!!, {
            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, it)
                type = "text/plain"
            }
            startActivity(shareIntent)
        })

        /**
         * Genre call observer
         */
        viewModel.genreResponse.observe(binding.lifecycleOwner!!, {
            viewModel.hideProgressGenre.postValue(false)
            genreAdapter = GenreAdapter(
                it?.data ?: ArrayList()
            )
            binding.genreAdapter = genreAdapter
            genreAdapter.notifyDataSetChanged()
        })

        /**
         * episodes call observer
         */
        viewModel.episodesResponse.observe(binding.lifecycleOwner!!, {
            viewModel.hideProgressBarEpisodes.postValue(false)
            episodesAdapter = EpisodesAdapter(
                it?.data ?: ArrayList()
            )
            binding.episodesAdapter = episodesAdapter
            episodesAdapter.notifyDataSetChanged()
        })

        /**
         * characters call observer
         */
        viewModel.charactersResponse.observe(binding.lifecycleOwner!!, {
            viewModel.hideProgressBarCharacters.postValue(false)
            charactersAdapter = CharactersAdapter(
                it?.included ?: ArrayList()
            )
            binding.charactersAdapter = charactersAdapter
            charactersAdapter.notifyDataSetChanged()
        })

        /**
         * Add favorites observer
         */
        viewModel.addFavorites.observe(binding.lifecycleOwner!!, Observer {
            if (it) {
                viewModel.sharedPreferencesRepository.saveFavorite(
                    context = this.context,
                    anime = viewModel.selectedAnime.value, manga = null
                )
            }
        })

        /**
         * Show default error message
         */
        viewModel.error.observe(binding.lifecycleOwner!!, {
            Toast.makeText(this.context, viewModel.errorMessage, Toast.LENGTH_LONG).show()
        })

        /**
         * Load all adpaters
         */
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