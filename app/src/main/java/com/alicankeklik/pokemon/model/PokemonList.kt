package com.alicankeklik.pokemon.model

import com.google.gson.annotations.SerializedName

data class PokemonList(  @SerializedName("results") val  result : List<Pokemon>) {

}
