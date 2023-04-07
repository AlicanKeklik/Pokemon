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
        getDataFromAPI(url)

    }


    private fun getDataFromAPI(url : String) {
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
                        error.value = false
                    }

                })
        )
    }
}