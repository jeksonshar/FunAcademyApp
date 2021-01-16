package com.jeksonshar.funacademyapp.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreListModel(

	@SerialName("genres")
	val genres: List<GenresItem>
)

@Serializable
data class GenresItem(

	@SerialName("name")
	val name: String,
	@SerialName("id")
	val id: Int
)
