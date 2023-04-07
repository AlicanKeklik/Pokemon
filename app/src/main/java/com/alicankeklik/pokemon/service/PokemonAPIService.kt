package com.alicankeklik.pokemon.service

import com.alicankeklik.pokemon.model.PokemonList
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PokemonAPIService {
    private val BASE_URL = "https://pokeapi.co/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(PokemonAPI::class.java)
fun getData(): Single<PokemonList> {
    return api.getPokemons()
}

}