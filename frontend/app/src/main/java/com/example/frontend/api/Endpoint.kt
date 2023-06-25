package com.example.frontend.api

import com.example.frontend.model.Acao
import retrofit2.Call
import retrofit2.http.*

interface Endpoint {
    @GET("/api/acoes")
    fun getTodasAcoes(): Call<List<Acao>>

    @GET("/api/acao/{id}")
    fun getAcao(@Path("id") id: Int): Call<Acao>

    @POST("/api/acao")
    fun salvarAcao(@Body acao: Acao): Call<Acao>

    @PUT("/api/acao/{id}")
    fun atualizarAcao(@Path("id") id: Int, @Body acao: Acao): Call<Acao>
}
