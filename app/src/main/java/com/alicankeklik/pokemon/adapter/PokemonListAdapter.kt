package com.alicankeklik.pokemon.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.alicankeklik.pokemon.databinding.ItemPokemonBinding
import com.alicankeklik.pokemon.model.Pokemon
import com.alicankeklik.pokemon.view.PermissionFragmentDirections

class PokemonListAdapter(val pokemonList: ArrayList<Pokemon> ): RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>() {
    val TAG = "PokemonListAdapter"

    class PokemonViewHolder(var binding: ItemPokemonBinding) :RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.binding.name.text = pokemonList.get(position).pokemonName

        holder.binding.root.setOnClickListener {
            val action = PermissionFragmentDirections.actionPermissionFragmentToListScreenFragment((position +1).toString())
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        Log.e(TAG, "pokemonsize: ${pokemonList.size} ", )
       return pokemonList.size
    }

    fun updatePokemonList(newpokemonList: List<Pokemon>) {
        pokemonList.clear()
        pokemonList.addAll(newpokemonList)
        notifyDataSetChanged()
    }
}