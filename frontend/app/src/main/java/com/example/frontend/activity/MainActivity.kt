package com.example.frontend.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.frontend.R
import com.example.frontend.adapter.RecyclerViewAdapter
import com.example.frontend.api.Endpoint
import com.example.frontend.model.Acao
import com.example.frontend.network.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = RecyclerViewAdapter(emptyList())
        recyclerView.adapter = adapter

        fetchDataFromApi()
    }


    private fun fetchDataFromApi() {
        val endpoint = RetrofitInitializer.retrofit.create(Endpoint::class.java)
        val call = endpoint.getTodasAcoes()

        endpoint.getTodasAcoes().enqueue(object : Callback<List<Acao>> {
            override fun onResponse(call: Call<List<Acao>>, response: Response<List<Acao>>) {
                if (response.isSuccessful) {
                    val acoes = response.body()
                    if (acoes != null) {
                        adapter = RecyclerViewAdapter(acoes)
                        recyclerView.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<List<Acao>>, t: Throwable) {
                println("Erro ao fazer requisição!")
            }
        })
    }
}
