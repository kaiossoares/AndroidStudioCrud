package com.example.frontend.api

import com.example.frontend.model.Acao
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Endpoint {
    @GET("/api/acoes")
    fun getTodasAcoes(): Call<List<Acao>>

    @POST("/api/acao")
    fun salvarAcao(@Body acao: Acao): Call<Acao>
}
