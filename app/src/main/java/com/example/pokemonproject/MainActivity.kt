package com.example.pokemonproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.pokemonproject.R
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    private lateinit var pokeList: MutableList<MutableList<String>>
    private lateinit var rvPoke: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pokeList = mutableListOf()
        rvPoke = findViewById(R.id.pokemon_list)
        getPokeImageURL()
    }

    private fun getPokeImageURL() {
        val client = AsyncHttpClient()
        val baseurl = "https://pokeapi.co/api/v2/pokemon/"
        for (i in 0 until 21) {
            val fullurl = baseurl + i.toString()
            client[fullurl, object : JsonHttpResponseHandler() {
                override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                    Log.d("Poke Success", "$json")
                    val pokeName = json.jsonObject.getString("name")
                    val pokeHeight = json.jsonObject.getString("height")
                    val pokeImage = json.jsonObject.getJSONObject("sprites").getString("back_default")
                    val row = mutableListOf<String>()
                    row.add(0, pokeName)
                    row.add(1, pokeHeight)
                    row.add(2, pokeImage)
                    pokeList.add(row)
                    val adapter = PokemonAdapter(pokeList)
                    rvPoke.adapter = adapter
                    rvPoke.layoutManager = LinearLayoutManager(this@MainActivity)
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String,
                    throwable: Throwable?
                ) {
                    Log.d("Poke Error", errorResponse)
                }
            }]
        }
    }
}