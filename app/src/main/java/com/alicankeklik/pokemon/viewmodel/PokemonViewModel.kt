package com.alicankeklik.pokemon.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alicankeklik.pokemon.model.PokemonDetail
import com.alicankeklik.pokemon.service.PokemonDetailService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PokemonViewModel () :  ViewModel() {

    val pokemons = MutableLiveData<PokemonDetail>()
    val error = MutableLiveData<Boolean>()

    private val pokemonDetailAPI = PokemonDetailService()
    private val disposable = CompositeDisposable()

    fun loadData( url: String){
        /*  val pokemon = Pokemon("deneme1","asdsdaa")
          val pokemon2 = Pokemon("deneme2","asdaa")
          val pokemon3 = Pokemon("deneme2","asdaa")

          val pokemonList = arrayListOf<Pokemon>(pokemon,pokemon2,pokemon3)

          pokemons.value = pokemonList*/
        getDataFromAPI(url)

    }


    private fun getDataFromAPI(url : String) {
        // countryLoading.value = true

        disposable.add(
            pokemonDetailAPI.getData(url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<PokemonDetail>(){


                    override fun onError(e: Throwable) {
                        error.value = true
                        Log.e("deneme","hattaa")
                    }

                    override fun onSuccess(t: PokemonDetail) {
                        var pokemonDetail = PokemonDetail(t.pokemonDetailName.toString(),t.pokemonDetailImgUrl,
                            t.pokemonDetailHeight.toString(),t.pokemonDetailWeight.toString())
                            pokemons.value = pokemonDetail
//                        pokemons.value?.pokemonDetailHeight = t.pokemonDetailHeight.toString()
//                        pokemons.value?.pokemonDetailImgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
//                        pokemons.value?.pokemonDetailName = t.pokemonDetailName.toString()
//                        pokemons.value?.pokemonDetailWeight = t.pokemonDetailWeight.toString()
//                   Log.e("Alican","pokemon name1 :"+t.pokemonDetailName.toString())


                        error.value = false
                    }

                })
        )
    }
}