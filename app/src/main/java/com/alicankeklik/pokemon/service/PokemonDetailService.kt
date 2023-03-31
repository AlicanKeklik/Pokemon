package com.alicankeklik.pokemon.service

import com.alicankeklik.pokemon.model.PokemonDetail
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Url

class PokemonDetailService {
    private val BASE_URL = "https://pokeapi.co/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(PokemonDetailAPI::class.java)

    fun getData( url : String ): Single<PokemonDetail> {
        return api.getPokemonDetail(url)
    }

}