package com.alicankeklik.pokemon.view

import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alicankeklik.pokemon.R
import com.alicankeklik.pokemon.databinding.FragmentListScreenBinding
import com.alicankeklik.pokemon.databinding.FragmentPermissionBinding
import com.alicankeklik.pokemon.viewmodel.PokemonListViewModel
import com.alicankeklik.pokemon.viewmodel.PokemonViewModel
import com.squareup.picasso.Picasso

class ListScreenFragment : Fragment() {
   private lateinit var  binding: FragmentListScreenBinding
   private lateinit var viewModel: PokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var  ApiUrl :String = ""
        arguments?.let {
            ApiUrl  = ListScreenFragmentArgs.fromBundle(it).pokemonDetailUrl
        }
        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
        observeLiveData()
        viewModel.loadData(ApiUrl)



    }
    fun observeLiveData(){
        viewModel.pokemons.observe(viewLifecycleOwner,{
            it?.let {
                binding.textView.text = it.pokemonDetailName
                binding.textView2.text = it.pokemonDetailHeight
                binding.textView3.text = it.pokemonDetailWeight
                Picasso.with(context).load(it.pokemonDetailImgUrl?.url).into(binding.imageView)
                Log.e("Alican","pokemon name :"+it.pokemonDetailName.toString())
            }


        })

        viewModel.error.observe(viewLifecycleOwner,{
            it?.let {
                if (it)
                    Toast.makeText(context,"No Network Connection Please Check Your Connection",
                        Toast.LENGTH_LONG).show()
            }

        })
    }

}