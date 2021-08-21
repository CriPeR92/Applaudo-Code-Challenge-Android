package com.example.applaudocodechallengeandroid.ui.mangaDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.applaudocodechallengeandroid.R
import com.example.applaudocodechallengeandroid.data.repository.SharedPreferencesRepository
import com.example.applaudocodechallengeandroid.databinding.FragmentMangaDetailsBinding
import com.example.applaudocodechallengeandroid.model.Anime
import com.example.applaudocodechallengeandroid.model.Manga
import com.example.applaudocodechallengeandroid.ui.animeDetails.CharactersAdapter
import com.example.applaudocodechallengeandroid.ui.animeDetails.DetailsViewModel
import com.example.applaudocodechallengeandroid.ui.animeDetails.EpisodesAdapter
import com.example.applaudocodechallengeandroid.ui.animeDetails.GenreAdapter
import com.google.gson.Gson
import org.koin.android.viewmodel.ext.android.viewModel

class MangaDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMangaDetailsBinding
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var chapterAdapter: ChaptersAdapter
    private lateinit var charactersAdapter: CharactersAdapter
    private var sharedPreferencesRepository: SharedPreferencesRepository = SharedPreferencesRepository()
    private val viewModel: DetailsMangaViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.selectedManga.value = Gson().fromJson(arguments?.getString("manga"), Manga::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_manga_details,
            container,
            false
        )
        binding.viewModel = this.viewModel
        binding.manga = viewModel.selectedManga.value?.attributes
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

        viewModel.chaptersResponse.observe(binding.lifecycleOwner!!, {
            viewModel.hideProgressBarChapter.postValue(false)
            chapterAdapter = ChaptersAdapter(
                it.data ?: ArrayList()
            )
            binding.chapterAdapter = chapterAdapter
            chapterAdapter.notifyDataSetChanged()
        })

        viewModel.charactersResponse.observe(binding.lifecycleOwner!!, {
            viewModel.hideProgressBarCharacters.postValue(false)
            charactersAdapter = CharactersAdapter(
                it.included ?: ArrayList()
            )
            binding.charactersAdapter = charactersAdapter
            charactersAdapter.notifyDataSetChanged()
        })

        viewModel.addFavorites.observe(binding.lifecycleOwner!!, {
            if (it) {
                sharedPreferencesRepository.saveFavorite(
                    context = this.context,
                    manga = viewModel.selectedManga.value, anime = null
                )
            }
        })

        viewModel.error.observe(binding.lifecycleOwner!!, {
            Toast.makeText(this.context, viewModel.errorMessage, Toast.LENGTH_LONG).show()
        })

        if (!::genreAdapter.isInitialized) {
            viewModel.getGenres()
            genreAdapter = GenreAdapter(ArrayList())
            binding.genreAdapter = genreAdapter
        } else {
            binding.genreAdapter = genreAdapter
        }

        if (!::chapterAdapter.isInitialized) {
            viewModel.getChapters()
            chapterAdapter = ChaptersAdapter(ArrayList())
            binding.chapterAdapter = chapterAdapter
        } else {
            binding.chapterAdapter = chapterAdapter
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