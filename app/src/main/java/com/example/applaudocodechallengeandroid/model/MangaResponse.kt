package com.example.applaudocodechallengeandroid.model

import com.google.gson.annotations.SerializedName

class MainMangaResponse(
    @SerializedName("data") var data: ArrayList<Manga>?,
    @SerializedName("meta") var meta: Meta?,
    @SerializedName("links") var links: Links?,
)

class Manga(
    @SerializedName("id") var id: String?,
    @SerializedName("type") var type: String?,
    @SerializedName("links") var links: Any?,
    @SerializedName("attributes") var attributes: MangaAttributes?,
    @SerializedName("relationships") var relationships: Any?,
)

class MangaAttributes(
    @SerializedName("createdAt") var createdAt: String?,
    @SerializedName("updatedAt") var updatedAt: String?,
    @SerializedName("slug") var slug: String?,
    @SerializedName("synopsis") var synopsis: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("coverImageTopOffset") var coverImageTopOffset: Int?,
    @SerializedName("titles") var titles: Titles?,
    @SerializedName("canonicalTitle") var canonicalTitle: String?,
    @SerializedName("abbreviatedTitles") var abbreviatedTitles: ArrayList<String>?,
    @SerializedName("averageRating") var averageRating: String?,
    @SerializedName("ratingFrequencies") var ratingFrequencies: Any?,
    @SerializedName("userCount") var userCount: Int?,
    @SerializedName("favoritesCount") var favoritesCount: Int?,
    @SerializedName("startDate") var startDate: String?,
    @SerializedName("endDate") var endDate: String?,
    @SerializedName("nextRelease") var nextRelease: String?,
    @SerializedName("popularityRank") var popularityRank: Int?,
    @SerializedName("ratingRank") var ratingRank: Int?,
    @SerializedName("ageRating") var ageRating: String?,
    @SerializedName("ageRatingGuide") var ageRatingGuide: String?,
    @SerializedName("subtype") var subtype: String?,
    @SerializedName("status") var status: String?,
    @SerializedName("tba") var tba: String?,
    @SerializedName("posterImage") var posterImage: Images?,
    @SerializedName("coverImage") var coverImage: Images?,
    @SerializedName("chapterCount") var chapterCount: Int?,
    @SerializedName("volumeCount") var volumeCount: Int?,
    @SerializedName("serialization") var serialization: String?,
    @SerializedName("mangaType") var mangaType: String?,
)