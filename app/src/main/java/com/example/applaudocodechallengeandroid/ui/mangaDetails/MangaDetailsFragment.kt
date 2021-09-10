package com.example.applaudocodechallengeandroid.ui.mangaDetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.applaudocodechallengeandroid.R
import com.example.applaudocodechallengeandroid.databinding.FragmentMangaDetailsBinding
import com.example.applaudocodechallengeandroid.model.Manga
import com.example.applaudocodechallengeandroid.ui.MainActivity
import com.example.applaudocodechallengeandroid.ui.animeDetails.CharactersAdapter
import com.example.applaudocodechallengeandroid.ui.animeDetails.GenreAdapter
import com.google.gson.Gson
import javax.inject.Inject

/**
 * Manga Details Fragment: Shows all details of an Manga selected
 */

class MangaDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModel: DetailsMangaViewModel

    private lateinit var binding: FragmentMangaDetailsBinding
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var chapterAdapter: ChaptersAdapter
    private lateinit var charactersAdapter: CharactersAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).mainComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.selectedManga.value =
            Gson().fromJson(arguments?.getString("manga"), Manga::class.java)
        viewModel.isFavorite.value =
            arguments?.getBoolean(resources.getString(R.string.is_favorite), false)
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

        /**
         * Share manga Name
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
         * chapters call observer
         */
        viewModel.chaptersResponse.observe(binding.lifecycleOwner!!, {
            viewModel.hideProgressBarChapter.postValue(false)
            chapterAdapter = ChaptersAdapter(
                it?.data ?: ArrayList()
            )
            binding.chapterAdapter = chapterAdapter
            chapterAdapter.notifyDataSetChanged()
        })

        /**
         * Characters call observer
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
         * add favorites observer
         */
        viewModel.addFavorites.observe(binding.lifecycleOwner!!, {
            if (it) {
                viewModel.sharedPreferencesRepository.saveFavorite(
                    context = this.context,
                    manga = viewModel.selectedManga.value, anime = null
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
         * Adapters
         */
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