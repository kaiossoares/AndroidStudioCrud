package com.example.frontend.activity

import android.os.Bundle
import android.widget.Button
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
import android.content.Intent

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

        adapter.setOnItemClickListener(object : RecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(acao: Acao) {
                val intent = Intent(this@MainActivity, AlterActivity::class.java)
                startActivity(intent)
            }
        })

        val btnRegisterPage = findViewById<Button>(R.id.btnRegisterPage)
        btnRegisterPage.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    private fun fetchDataFromApi() {
        val endpoint = RetrofitInitializer.retrofit.create(Endpoint::class.java)

        endpoint.getTodasAcoes().enqueue(object : Callback<List<Acao>> {
            override fun onResponse(call: Call<List<Acao>>, response: Response<List<Acao>>) {
                if (response.isSuccessful) {
                    val acoes = response.body()
                    if (acoes != null) {
                        val acoesOrdenadas = acoes.sortedBy { it.id }
                        adapter = RecyclerViewAdapter(acoesOrdenadas)
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
