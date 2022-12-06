package com.alicankeklik.pokemon.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alicankeklik.pokemon.model.Pokemon

class PokemonListViewModel :ViewModel() {
    val pokemons = MutableLiveData<List<Pokemon>>()
    val error = MutableLiveData<Boolean>()


    fun loadData(){
        val pokemon = Pokemon("deneme1","asdsdaa")
        val pokemon2 = Pokemon("deneme2","asdaa")
        val pokemon3 = Pokemon("deneme2","asdaa")

        val pokemonList = arrayListOf<Pokemon>(pokemon,pokemon2,pokemon3)

        pokemons.value = pokemonList


    }
}