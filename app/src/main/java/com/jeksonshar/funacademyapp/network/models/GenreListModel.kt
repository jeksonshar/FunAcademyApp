package com.jeksonshar.funacademyapp.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreListModel(

	@SerialName("genres")
	val genres: List<GenresItem>? = null
)

@Serializable
data class GenresItem(

	@SerialName("name")
	val name: String? = null,
	@SerialName("id")
	val id: Int? = null
)
