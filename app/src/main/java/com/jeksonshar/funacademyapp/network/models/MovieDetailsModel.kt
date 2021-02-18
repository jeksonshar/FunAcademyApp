package com.jeksonshar.funacademyapp.network.models

import com.jeksonshar.funacademyapp.data.Genre
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsModel(

	@SerialName("original_language")
	val originalLanguage: String,
	@SerialName("imdb_id")
	val imdbId: String,
	@SerialName("video")
	val video: Boolean,
	@SerialName("title")
	val title: String? = null,
	@SerialName("backdrop_path")
	val backdropPath: String? = null,
	@SerialName("revenue")
	val revenue: Int,
	@SerialName("genres")
	val genres: List<Genre>? = emptyList(),
	@SerialName("popularity")
	val popularity: Float? = null,
	@SerialName("production_countries")
	val productionCountries: List<ProductionCountriesItem>,
	@SerialName("id")
	val id: Int? = null,
	@SerialName("vote_count")
	val voteCount: Int? = null,
	@SerialName("budget")
	val budget: Int,
	@SerialName("overview")
	val overview: String? = null,
	@SerialName("original_title")
	val originalTitle: String,
	@SerialName("runtime")
	val runtime: Int? = null,
	@SerialName("poster_path")
	val posterPath: String?,
	@SerialName("release_date")
	val releaseDate: String,
	@SerialName("vote_average")
	val voteAverage: Float? = null,
	@SerialName("tagline")
	val tagline: String,
	@SerialName("adult")
	val adult: Boolean? = null,
	@SerialName("homepage")
	val homepage: String,
	@SerialName("status")
	val status: String
)

@Serializable
data class ProductionCountriesItem(

	@SerialName("iso_3166_1")
	val iso31661: String,
	@SerialName("name")
	val name: String
)

