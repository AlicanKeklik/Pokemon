package com.alicankeklik.pokemon.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alicankeklik.pokemon.model.Pokemon
import com.alicankeklik.pokemon.model.PokemonList
import com.alicankeklik.pokemon.service.PokemonAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PokemonListViewModel :ViewModel() {
    val pokemons = MutableLiveData<List<Pokemon>>()

    private val pokemonAPIService = PokemonAPIService()
    private val disposable = CompositeDisposable()

    fun loadData(){
      /*  val pokemon = Pokemon("deneme1","asdsdaa")
        val pokemon2 = Pokemon("deneme2","asdaa")
        val pokemon3 = Pokemon("deneme2","asdaa")

        val pokemonList = arrayListOf<Pokemon>(pokemon,pokemon2,pokemon3)

        pokemons.value = pokemonList*/
     getDataFromAPI()

    }


    private fun getDataFromAPI() {
       // countryLoading.value = true

        disposable.add(
            pokemonAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<PokemonList>(){


                    override fun onError(e: Throwable) {
            Log.e("deneme","hattaa")
                    }

                    override fun onSuccess(t: PokemonList) {
                        pokemons.value = t.results
                    }

                })
        )
    }
}