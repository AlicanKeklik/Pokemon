package com.alicankeklik.pokemon.service

import com.alicankeklik.pokemon.model.PokemonDetail
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface PokemonDetailAPI {

    @GET("api/v2/pokemon/{url}/")
    fun getPokemonDetail( @Path("url") url : String): Single<PokemonDetail>
}