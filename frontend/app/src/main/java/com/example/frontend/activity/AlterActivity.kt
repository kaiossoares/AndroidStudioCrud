package com.example.frontend.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.frontend.R
import com.example.frontend.api.Endpoint
import com.example.frontend.model.Acao
import com.example.frontend.network.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
        val btnAlterar = findViewById<Button>(R.id.btnAlterar)

        editTextNome.setText(nome)
        editTextTipo.setText(tipo)
        editTextDescricao.setText(descricao)

        btnAlterar.setOnClickListener{
            val novoNome = editTextNome.text.toString()
            val novoTipo = editTextTipo.text.toString()
            val novaDescricao = editTextDescricao.text.toString()

            val acao = Acao(id, novoNome, novoTipo, novaDescricao)

            atualizarAcao(acao)
        }
    }

    private fun atualizarAcao(acao: Acao) {
        val endpoint = RetrofitInitializer.retrofit.create(Endpoint::class.java)
        val call = endpoint.atualizarAcao(acao.id, acao)

        call.enqueue(object : Callback<Acao> {
            override fun onResponse(call: Call<Acao>, response: Response<Acao>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@AlterActivity, "Ação atualizada com sucesso!", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this@AlterActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@AlterActivity, "Erro ao atualizar ação.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Acao>, t: Throwable) {
                Log.e("AlterActivity", "Falha na comunicação com a API", t)
                Toast.makeText(this@AlterActivity, "Falha na comunicação com a API.", Toast.LENGTH_SHORT).show()
            }
        })
    }

}