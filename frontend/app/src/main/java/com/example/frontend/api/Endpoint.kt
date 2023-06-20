package com.example.frontend.api

import com.example.frontend.model.Acao
import retrofit2.Call
import retrofit2.http.GET

interface Endpoint {
    @GET("/api/acoes")
    fun getTodasAcoes(): Call<List<Acao>>
}
