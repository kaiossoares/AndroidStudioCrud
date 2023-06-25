package com.example.frontend.activity

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.frontend.R


class AlterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        title = "Alterar Ação"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alter)

        val id = intent.getIntExtra("id", 0)
        val nome = intent.getStringExtra("nome")
        val tipo = intent.getStringExtra("tipo")
        val descricao = intent.getStringExtra("descricao")

        val editTextNome = findViewById<EditText>(R.id.editTextNome)
        val editTextTipo = findViewById<EditText>(R.id.editTextTipo)
        val editTextDescricao = findViewById<EditText>(R.id.editTextDescricao)

        editTextNome.setText(nome)
        editTextTipo.setText(tipo)
        editTextDescricao.setText(descricao)
    }
}