package com.alicankeklik.pokemon.view


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alicankeklik.pokemon.adapter.PokemonListAdapter
import com.alicankeklik.pokemon.databinding.FragmentPermissionBinding
import com.alicankeklik.pokemon.viewmodel.PokemonListViewModel


class PermissionFragment : Fragment() {
    private lateinit var binding: FragmentPermissionBinding
    private lateinit var viewModel: PokemonListViewModel
    private   var pokemonListAdapter = PokemonListAdapter(arrayListOf())
    private var TAG: String = "PermissionFragment"
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    //with this code when user change the setting user will back application
    val checkOverlaySetting: Runnable = object : Runnable {
        override fun run() {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                return
            }
            if (Settings.canDrawOverlays(requireActivity())) {
                //You have the permission, re-launch MainActivity
                val i = Intent(requireActivity(), MainActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(i)
                return
            }
            handler.postDelayed(this, 1000)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPermissionBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PokemonListViewModel::class.java)
        observeLiveData()
        binding.pokemonList.layoutManager = LinearLayoutManager(requireContext())
        binding.pokemonList.adapter = pokemonListAdapter
        if (!Settings.canDrawOverlays(requireContext())) {
            binding.overlayPermissonButton.visibility = View.VISIBLE
            binding.pokemonList.visibility = View.GONE
        } else {
            binding.overlayPermissonButton.visibility = View.GONE
            binding.pokemonList.visibility = View.VISIBLE
            viewModel.loadData()

        }
        binding.overlayPermissonButton.setOnClickListener {
            requestpermission()
            Log.e(TAG, "buton")
        }

    }


    private fun requestpermission() {
        when {
            Settings.canDrawOverlays(requireContext()) -> {
                binding.overlayPermissonButton.visibility = View.GONE
                binding.pokemonList.visibility = View.VISIBLE
                viewModel.loadData()
                Log.e(TAG, "second fragment can work")
            }
            else -> {
                // You can directly ask for the permission.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.overlayPermissonButton.visibility = View.VISIBLE
                    binding.pokemonList.visibility = View.GONE
                    val myIntent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
                    startActivity(myIntent)
                    handler.postDelayed(checkOverlaySetting, 1000);
                }

            }
        }


    }

    fun observeLiveData(){
        viewModel.pokemons.observe(viewLifecycleOwner,{pokemons ->
            pokemons?.let {
                binding.pokemonList.visibility = View.VISIBLE
                pokemonListAdapter.updatePokemonList(pokemons)
            }

        })

    }


}






