package com.alicankeklik.pokemon.view



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alicankeklik.pokemon.databinding.FragmentListScreenBinding
import com.alicankeklik.pokemon.viewmodel.PokemonViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.overlay_screen.view.*


class ListScreenFragment : Fragment() {
   private lateinit var  binding: FragmentListScreenBinding
   private lateinit var viewModel: PokemonViewModel
   private lateinit var testView : View



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        testView = ( activity as  MainActivity).gettestView()
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

        binding.button.setOnClickListener{
          // overlaycall
           startService()
       }

    }
    fun startService() {
        testView.visibility = View.VISIBLE
        viewModel.pokemons.observe(viewLifecycleOwner, {
                testView.overlayname.text = it.pokemonDetailName
                Picasso.with(context).load(it.pokemonDetailImgUrl?.url.toString()).resize(400, 400)
                    .centerInside().into(testView.viewFront)
                Picasso.with(context).load(it.pokemonDetailImgUrl?.urlBack.toString())
                    .resize(400, 400).centerInside().into(testView.viewBack)
            })
        testView?.let {
            it.closeoverlay.setOnClickListener{
                testView.visibility = View.GONE
            } }

    }

    fun observeLiveData(){
        viewModel.pokemons.observe(viewLifecycleOwner,{
            it?.let {
                binding.textView.text = it.pokemonDetailName
                binding.textView2.text = it.pokemonDetailHeight
                binding.textView3.text = it.pokemonDetailWeight
                Picasso.with(context).load(it.pokemonDetailImgUrl?.url.toString()).resize(400,400).centerInside().into(binding.imageView)
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