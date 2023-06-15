package com.example.frontend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var acao = Acao()
        acao.nome = "XPML11"
        acao.tipo = "Fundo Imobiliário"
        acao.descricao = "Fundo criado em 2017, sendo considerado um fundo de shopping."

        var gson = Gson()

        var acaoJson = gson.toJson(acao)

        println("-------> " + acaoJson)

        // Convertendo o json em um objeto Acao
        var acao2 = Acao()
        acao2 = gson.fromJson(acaoJson, Acao::class.java)

        println("-------> objeto convertido em json " + acao2)
    }
}