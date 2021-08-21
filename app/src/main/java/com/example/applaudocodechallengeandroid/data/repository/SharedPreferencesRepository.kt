package com.example.applaudocodechallengeandroid.data.repository

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.example.applaudocodechallengeandroid.R
import com.example.applaudocodechallengeandroid.model.Anime
import com.example.applaudocodechallengeandroid.model.Manga
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferencesRepository {

    private var prefs: SharedPreferences? = null
    private var animeList: ArrayList<Anime>? = null
    private var mangaList: ArrayList<Manga>? = null
    private val typeAnime = object : TypeToken<ArrayList<Anime>>() {}.type
    private val typeManga = object : TypeToken<ArrayList<Manga>>() {}.type

    fun saveFavorite(context: Context?, anime: Anime?, manga: Manga?) {
        prefs = context?.getSharedPreferences(
            context.resources
                .getString(R.string.favorites), Context.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor? = prefs?.edit()

        if (manga == null) {
            animeList = Gson().fromJson(prefs?.getString("animeList", null), typeAnime) ?: ArrayList()
            if (animeList?.firstOrNull { ob ->
                    ob.id == anime?.id
                } == null) {

                anime?.let { animeList?.add(it) }
                editor?.putString("animeList", Gson().toJson(animeList))
                Toast.makeText(context, R.string.added, Toast.LENGTH_LONG).show()
                editor?.apply()
            } else {
                Toast.makeText(context, R.string.inList, Toast.LENGTH_LONG).show()
            }
        } else {
            mangaList = Gson().fromJson(prefs?.getString("mangaList", null), typeManga) ?: ArrayList()
            if (mangaList?.firstOrNull { ob ->
                    ob.id == manga.id
                } == null) {
                manga.let { mangaList?.add(it) }
                editor?.putString("mangaList", Gson().toJson(mangaList))
                Toast.makeText(context, R.string.added, Toast.LENGTH_LONG).show()
                editor?.apply()
            } else {
                Toast.makeText(context, R.string.inList, Toast.LENGTH_LONG).show()
            }
        }

    }

    fun getAnimeFavorites(context: Context?) : ArrayList<Anime> {
        prefs = context?.getSharedPreferences(context.resources
            .getString(R.string.favorites), Context.MODE_PRIVATE)
        return Gson().fromJson(prefs!!.getString("animeList", null), typeAnime)
    }

    fun getMangaFavorites(context: Context?) : ArrayList<Manga> {
        prefs = context?.getSharedPreferences(context.resources
            .getString(R.string.favorites), Context.MODE_PRIVATE)
        return Gson().fromJson(prefs!!.getString("mangaList", null), typeManga)
    }

    fun deleteFavorite() {

    }
}