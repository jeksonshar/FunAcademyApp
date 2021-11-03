package com.jeksonshar.funacademyapp.network.models

import com.jeksonshar.funacademyapp.data.Genre
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsModel(

	@SerialName("original_language")
	val originalLanguage: String? = null,
	@SerialName("imdb_id")
	val imdbId: String? = null,
	@SerialName("video")
	val video: Boolean? = null,
	@SerialName("title")
	val title: String? = null,
	@SerialName("backdrop_path")
	val backdropPath: String? = null,
	@SerialName("revenue")
	val revenue: Float? = null,
	@SerialName("genres")
	val genres: List<Genre>? = null,
	@SerialName("popularity")
	val popularity: Float? = null,
	@SerialName("production_countries")
	val productionCountries: List<ProductionCountriesItem?>? = null,
	@SerialName("id")
	val id: Int? = null,
	@SerialName("vote_count")
	val voteCount: Int? = null,
	@SerialName("budget")
	val budget: Int? = null,
	@SerialName("overview")
	val overview: String? = null,
	@SerialName("original_title")
	val originalTitle: String? = null,
	@SerialName("runtime")
	val runtime: Int? = null,
	@SerialName("poster_path")
	val posterPath: String? = null,
	@SerialName("release_date")
	val releaseDate: String? = null,
	@SerialName("vote_average")
	val voteAverage: Float? = null,
	@SerialName("tagline")
	val tagline: String? = null,
	@SerialName("adult")
	val adult: Boolean? = null,
	@SerialName("homepage")
	val homepage: String? = null,
	@SerialName("status")
	val status: String? = null
)

@Serializable
data class ProductionCountriesItem(

	@SerialName("iso_3166_1")
	val iso31661: String? = null,
	@SerialName("name")
	val name: String? = null
)