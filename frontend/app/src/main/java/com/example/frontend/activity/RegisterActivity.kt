package com.example.frontend.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.frontend.R
import com.example.frontend.api.Endpoint
import com.example.frontend.model.Acao
import com.example.frontend.network.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log

class RegisterActivity : AppCompatActivity() {

    private lateinit var editTextNome: EditText
    private lateinit var editTextTipo: EditText
    private lateinit var editTextDescricao: EditText
    private lateinit var btnCadastrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        editTextNome = findViewById(R.id.editTextNome)
        editTextTipo = findViewById(R.id.editTextTipo)
        editTextDescricao = findViewById(R.id.editTextDescricao)
        btnCadastrar = findViewById(R.id.btnCadastrar)

        btnCadastrar.setOnClickListener {
            val nome = editTextNome.text.toString()
            val tipo = editTextTipo.text.toString()
            val descricao = editTextDescricao.text.toString()

            val acao = Acao(0, nome, tipo, descricao)

            salvarAcao(acao)
        }
    }

    private fun salvarAcao(acao: Acao) {
        Log.d("RegisterActivity", "Chamando salvarAcao()")

        val endpoint = RetrofitInitializer.retrofit.create(Endpoint::class.java)
        val call = endpoint.salvarAcao(acao)

        call.enqueue(object : Callback<Acao> {
            override fun onResponse(call: Call<Acao>, response: Response<Acao>) {
                if (response.isSuccessful) {
                    Log.d("RegisterActivity", "Resposta da API bem-sucedida")
                    Toast.makeText(this@RegisterActivity, "Ação cadastrada com sucesso!", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Log.d("RegisterActivity", "Resposta da API com erro")
                    Toast.makeText(this@RegisterActivity, "Erro ao cadastrar ação.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Acao>, t: Throwable) {
                Log.e("RegisterActivity", "Falha na comunicação com a API", t)
                Toast.makeText(this@RegisterActivity, "Falha na comunicação com a API.", Toast.LENGTH_SHORT).show()
            }
        })
    }

}