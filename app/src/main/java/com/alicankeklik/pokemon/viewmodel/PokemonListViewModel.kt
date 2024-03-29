package com.alicankeklik.pokemon.viewmodel

import android.util.Log
import android.widget.Toast
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
    val error = MutableLiveData<Boolean>()

    private val pokemonAPIService = PokemonAPIService()
    private val disposable = CompositeDisposable()

    fun loadData(){
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
                        error.value = true
            Log.e("deneme","hattaa")
                    }

                    override fun onSuccess(t: PokemonList) {
                        pokemons.value = t.result
                        error.value = false
                    }

                })
        )
    }
}