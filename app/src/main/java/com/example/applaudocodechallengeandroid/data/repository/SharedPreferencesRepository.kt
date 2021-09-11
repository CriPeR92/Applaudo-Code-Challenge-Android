package com.example.applaudocodechallengeandroid.data.repository

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.example.applaudocodechallengeandroid.R
import com.example.applaudocodechallengeandroid.model.Anime
import com.example.applaudocodechallengeandroid.model.Manga
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

/**
 * Repository to save all anime or manga marked as favorites using SharedPreferences
 */

class SharedPreferencesRepository @Inject constructor() {

    private var prefs: SharedPreferences? = null
    private var animeList: ArrayList<Anime>? = null
    private var mangaList: ArrayList<Manga>? = null
    private val typeAnime = object : TypeToken<ArrayList<Anime>>() {}.type
    private val typeManga = object : TypeToken<ArrayList<Manga>>() {}.type

    /**
     * Save anime or manga
     */
    fun saveFavorite(context: Context?, anime: Anime?, manga: Manga?) {
        prefs = context?.getSharedPreferences(
            context.resources
                .getString(R.string.favorites), Context.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor? = prefs?.edit()

        if (manga == null) {
            animeList = Gson().fromJson(
                prefs?.getString(
                    context?.resources?.getString(R.string.anime_list),
                    null
                ), typeAnime
            ) ?: ArrayList()
            if (animeList?.firstOrNull { ob ->
                    ob.id == anime?.id
                } == null) {
                anime?.let { animeList?.add(it) }
                editor?.putString(
                    context?.resources?.getString(R.string.anime_list),
                    Gson().toJson(animeList)
                )
                Toast.makeText(context, R.string.added, Toast.LENGTH_LONG).show()
                editor?.apply()
            } else {
                Toast.makeText(context, R.string.inList, Toast.LENGTH_LONG).show()
            }
        } else {
            mangaList = Gson().fromJson(
                prefs?.getString(
                    context?.resources?.getString(R.string.manga_list),
                    null
                ), typeManga
            ) ?: ArrayList()
            if (mangaList?.firstOrNull { ob ->
                    ob.id == manga.id
                } == null) {
                manga.let { mangaList?.add(it) }
                editor?.putString(
                    context?.resources?.getString(R.string.manga_list),
                    Gson().toJson(mangaList)
                )
                Toast.makeText(context, R.string.added, Toast.LENGTH_LONG).show()
                editor?.apply()
            } else {
                Toast.makeText(context, R.string.inList, Toast.LENGTH_LONG).show()
            }
        }

    }

    /**
     * Get all anime previously saved
     */
    fun getAnimeFavorites(context: Context?): ArrayList<Anime>? {
        prefs = context?.getSharedPreferences(
            context.resources
                .getString(R.string.favorites), Context.MODE_PRIVATE
        )
        return Gson().fromJson(
            prefs?.getString(
                context?.resources?.getString(R.string.anime_list),
                null
            ), typeAnime
        )
    }

    /**
     * Get all manga previously saved
     */
    fun getMangaFavorites(context: Context?): ArrayList<Manga>? {
        prefs = context?.getSharedPreferences(
            context.resources
                .getString(R.string.favorites), Context.MODE_PRIVATE
        )
        return Gson().fromJson(
            prefs?.getString(
                context?.resources?.getString(R.string.manga_list),
                null
            ), typeManga
        )
    }

    /**
     * Delete anime from list of favorites
     */
    fun deleteAnime(context: Context?, animeList: ArrayList<Anime>?): ArrayList<Anime>? {
        prefs = context?.getSharedPreferences(
            context.resources
                .getString(R.string.favorites), Context.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor? = prefs?.edit()
        editor?.putString(
            context?.resources?.getString(R.string.anime_list),
            Gson().toJson(animeList)
        )
        editor?.apply()
        return animeList
    }

    /**
     * Delete manga from list of favorites
     */
    fun deleteManga(context: Context?, mangaList: ArrayList<Manga>?): ArrayList<Manga>? {
        prefs = context?.getSharedPreferences(
            context.resources
                .getString(R.string.favorites), Context.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor? = prefs?.edit()
        editor?.putString(
            context?.resources?.getString(R.string.manga_list),
            Gson().toJson(mangaList)
        )
        editor?.apply()
        return mangaList
    }
}