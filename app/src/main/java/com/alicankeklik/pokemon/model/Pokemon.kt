package com.alicankeklik.pokemon.model

import com.google.gson.annotations.SerializedName


data class Pokemon(
    @SerializedName("name")  val pokemonName:String?,
    @SerializedName("url") val pokemonDetailUrl:String?)
