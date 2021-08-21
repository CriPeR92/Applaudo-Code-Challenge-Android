package com.example.applaudocodechallengeandroid.model

import com.google.gson.annotations.SerializedName

class MainAnimeResponse(
    @SerializedName("data") var data: ArrayList<Anime>?,
    @SerializedName("meta") var meta: Meta?,
    @SerializedName("links") var links: Links?
)

class Meta(
    @SerializedName("count") var count: Int?,
)

class Links(
    @SerializedName("first") var first: String?,
    @SerializedName("next") var next: String?,
    @SerializedName("last") var last: String?,
    @SerializedName("prev") var prev: String?,
)

class Anime(
    @SerializedName("id") var id: String?,
    @SerializedName("type") var type: String?,
    @SerializedName("links") var links: Any?,
    @SerializedName("attributes") var attributes: AnimeAttributes?,
    @SerializedName("relationships") var relationships: Any?,
)

class Titles(
    @SerializedName("en") var en: String?,
    @SerializedName("en_us") var en_us: String?,
    @SerializedName("en_jp") var en_jp: String?,
    @SerializedName("ja_jp") var ja_jp: String?,
)

class Images(
    @SerializedName("tiny") var tiny: String?,
    @SerializedName("small") var small: String?,
    @SerializedName("medium") var medium: String?,
    @SerializedName("large") var large: String?,
    @SerializedName("original") var original: String?,
    @SerializedName("meta") var meta: Any?,
)

class AnimeAttributes(
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
    @SerializedName("episodeCount") var episodeCount: Int?,
    @SerializedName("episodeLength") var episodeLength: Int?,
    @SerializedName("totalLength") var totalLength: Int?,
    @SerializedName("youtubeVideoId") var youtubeVideoId: String?,
    @SerializedName("showType") var showType: String?,
    @SerializedName("nsfw") var nsfw: Boolean?,
)