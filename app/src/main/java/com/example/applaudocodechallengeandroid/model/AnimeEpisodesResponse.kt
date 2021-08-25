package com.example.applaudocodechallengeandroid.model

import com.google.gson.annotations.SerializedName

class AnimeEpisodesResponse(
    @SerializedName("data") var data: ArrayList<Episode>?,
    @SerializedName("meta") var meta: Meta?,
    @SerializedName("links") var links: Links?
)

class Episode(
    @SerializedName("id") var id: String?,
    @SerializedName("type") var type: String?,
    @SerializedName("links") var links: Any?,
    @SerializedName("attributes") var attributes: EpisodeAttributes?,
    @SerializedName("relationships") var relationships: Any?,
)

class EpisodeAttributes(
    @SerializedName("createdAt") var createdAt: String?,
    @SerializedName("updatedAt") var updatedAt: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("synopsis") var synopsis: String?,
    @SerializedName("titles") var titles: Titles?,
    @SerializedName("canonicalTitle") var canonicalTitle: String?,
    @SerializedName("seasonNumber") var seasonNumber: Int?,
    @SerializedName("number") var number: Int?,
    @SerializedName("relativeNumber") var relativeNumber: Int?,
    @SerializedName("airdate") var airdate: String?,
    @SerializedName("length") var length: Int?,
    @SerializedName("thumbnail") var thumbnail: Any?,
)