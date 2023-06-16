package com.example.frontend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        layoutManager = LinearLayoutManager (this)
        recyclerView.layoutManager = layoutManager

        adapter = RecyclerViewAdapter ()
        recyclerView.adapter = adapter

        val btnAdd = findViewById<Button>(R.id.btn_add)
        btnAdd.setOnClickListener {
            // Criar um Intent para iniciar a nova atividade
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

}