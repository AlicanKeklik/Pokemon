package com.alicankeklik.pokemon.model

import com.google.gson.annotations.SerializedName

data class PokemonDetail(@SerializedName("name") var pokemonDetailName:String?,
                         @SerializedName("sprites") var pokemonDetailImgUrl: Sprites = Sprites(null ,null),
                         @SerializedName("height") var pokemonDetailHeight:String?,
                         @SerializedName("weight") var pokemonDetailWeight:String?)

data class Sprites( @SerializedName("front_default") var url :String?,
                    @SerializedName("back_default") var urlBack:String? )