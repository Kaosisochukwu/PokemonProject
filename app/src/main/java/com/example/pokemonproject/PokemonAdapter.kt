package com.example.pokemonproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemonproject.R

class PokemonAdapter (private val pokeList: List<List<String>>) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val pokemonImage: ImageView
        val pokemonName: TextView
        val pokemonHeight: TextView

        init {
            // Find our RecyclerView item's ImageView for future use
            pokemonImage = view.findViewById(R.id.pokemon_image)
            pokemonName = view.findViewById(R.id.pokemon_name)
            pokemonHeight = view.findViewById(R.id.pokemon_height)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pokemonName.text = pokeList[position][0]
        holder.pokemonHeight.text = pokeList[position][1]
        Glide.with(holder.itemView)
            .load(pokeList[position][2])
            .centerCrop()
            .into(holder.pokemonImage)
    }

    override fun getItemCount(): Int {
        return pokeList.size
    }
}