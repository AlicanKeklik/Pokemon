package com.alicankeklik.pokemon.service

import com.alicankeklik.pokemon.model.PokemonList
import io.reactivex.Single
import retrofit2.http.GET

interface PokemonAPI {

    @GET("api/v2/pokemon")
    fun getPokemons(): Single<PokemonList>
}