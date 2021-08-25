package com.example.applaudocodechallengeandroid.ui.favorites

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
import com.example.applaudocodechallengeandroid.databinding.FragmentFavoritesBinding
import com.example.applaudocodechallengeandroid.model.Anime
import com.example.applaudocodechallengeandroid.model.Manga
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Favorites Fragment: Show all anime and manga saved before as favorites
 */

class FavoritesFragment : Fragment() {

    lateinit var animeAdapter: AnimeFavoriteAdapter
    lateinit var mangaAdapter: MangaFavoriteAdapter
    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel: FavoritesViewModel by viewModel()
    private val typeAnime = object : TypeToken<ArrayList<Anime>>() {}.type
    private val typeManga = object : TypeToken<ArrayList<Manga>>() {}.type
    var animeList: ArrayList<Anime>? = null
    var mangaList: ArrayList<Manga>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        animeList =
            Gson().fromJson(arguments?.getString(resources.getString(R.string.anime_)), typeAnime)
        mangaList =
            Gson().fromJson(arguments?.getString(resources.getString(R.string.manga_)), typeManga)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_favorites,
            container,
            false
        )
        binding.viewModel = this.viewModel
        binding.animeAdapter = AnimeFavoriteAdapter(this, animeList ?: ArrayList())
        binding.mangaAdapter = MangaFavoriteAdapter(this, mangaList ?: ArrayList())
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        /**
         * anime selected observer
         */
        viewModel.animeSelected.observe(binding.lifecycleOwner!!, {
            val bundle =
                bundleOf(
                    resources.getString(R.string.anime_) to Gson().toJson(it),
                    resources.getString(R.string.is_favorite) to true
                )
            findNavController().navigate(
                R.id.action_favoritesFragment_to_detailsFragment,
                bundle
            )
        })

        /**
         * manga selected observer
         */
        viewModel.mangaSelected.observe(binding.lifecycleOwner!!, {
            val bundle =
                bundleOf(
                    resources.getString(R.string.manga_) to Gson().toJson(it),
                    resources.getString(R.string.is_favorite) to true
                )
            findNavController().navigate(
                R.id.action_favoritesFragment_to_mangaDetailsFragment,
                bundle
            )
        })

        /**
         * removeAnime observer
         */
        viewModel.removeAnime.observe(binding.lifecycleOwner!!, {
            animeList?.removeAll { deleteAnime ->
                it.id == deleteAnime.id
            }
            viewModel.sharedPreferencesRepository.deleteAnime(context = context, animeList)
            animeAdapter = AnimeFavoriteAdapter(this, animeList ?: ArrayList())
            animeAdapter.notifyDataSetChanged()
            binding.animeAdapter = animeAdapter
            Toast.makeText(this.context, R.string.removed, Toast.LENGTH_LONG).show()
        })

        /**
         * removeManga observer
         */
        viewModel.removeManga.observe(binding.lifecycleOwner!!, {
            mangaList?.removeAll { deleteManga ->
                it.id == deleteManga.id
            }
            viewModel.sharedPreferencesRepository.deleteManga(context = context, mangaList)
            mangaAdapter = MangaFavoriteAdapter(this, mangaList ?: ArrayList())
            mangaAdapter.notifyDataSetChanged()
            binding.mangaAdapter = mangaAdapter
            Toast.makeText(this.context, R.string.removed, Toast.LENGTH_LONG).show()
        })
    }
}